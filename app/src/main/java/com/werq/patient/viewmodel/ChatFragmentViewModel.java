package com.werq.patient.viewmodel;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.ChatHistoryData;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessageData;
import com.werq.patient.service.model.RequestJsonPojo.NewChat;
import com.werq.patient.service.model.chat.Author;
import com.werq.patient.service.model.chat.Message;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

public class ChatFragmentViewModel extends BaseViewModel  {

    CompositeDisposable disposable;
    PatientRepository patientRepository;
    ApiCallback apiCallback = this;
    boolean isNewChat = false;
    MutableLiveData<String> typedMsg;
    MutableLiveData<ArrayList<Message>> messageListBefore;
    MutableLiveData<ArrayList<Message>> messageList;
    MutableLiveData<ArrayList<Message>> messageListAfter;
    MutableLiveData<Long> lastMessageTimestamp;
    MutableLiveData<Long> firstMessageTimestamp;
    int remainingHistoryMsgCount;// all chat msg count till today
    FirebaseDatabase database;
    DatabaseReference channelIdRef;
    SessionManager sessionManager;
    String channelId;
    boolean isFirstMsgSent=false;

    public ChatFragmentViewModel(String authToken) {
        super(authToken);
        database = FirebaseDatabase.getInstance();
        patientRepository = new PatientRepository();
        isNewChat = true;
        typedMsg = new MutableLiveData<>();
        messageListBefore = new MutableLiveData<>();
        messageList = new MutableLiveData<>();
        messageListAfter = new MutableLiveData<>();
        lastMessageTimestamp = new MutableLiveData<>();
        firstMessageTimestamp = new MutableLiveData<>();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    public void addSentMsgToAdapter(String message,Date msgDate){
        Message chatMessage = new Message();
        Author user = new Author();

        if(msgDate!=null){
            chatMessage.setId(msgDate.getTime()+"");
            chatMessage.setTimestamp(msgDate.getTime());
            chatMessage.setCreatedAt(msgDate);
        }

        user.setId(SessionManager.getUserId());
        user.setOnline(false);
        //user.setName(name);
        chatMessage.setUser(user);
        chatMessage.setText(message);

        ArrayList<Message> afterChatList = new ArrayList<>();
        afterChatList.add(chatMessage);
        messageListAfter.setValue(afterChatList);
    }



    public void sendMessageToServer(String message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChannel(channelId);
        sendMessage.setMessage(message);

        Date utcDate = null;
        try {
            utcDate=Helper.currentlocalDateToUtc();
            Helper.setLog("UTCDate",utcDate.toString());
            Helper.setLog("UTC",utcDate.getTime()+"");
            sendMessage.setTimeStamp(utcDate.getTime());

        } catch (ParseException e) {

            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }

        addSentMsgToAdapter(message,utcDate);

        patientRepository.sendMessageToServer(getAuthToken(), sendMessage, getToast(), apiCallback, "SendMessage");
        Helper.setLog("last msg timestamp msg sent",sendMessage.getTimeStamp()+"");
        lastMessageTimestamp.setValue(sendMessage.getTimeStamp());
    }

    public void fetchInitialChat() {

        try {
            String currentUtcTimestamp=Helper.currentlocalDateToUtc().getTime() + "";
            patientRepository.fetchHistoryChatList(getAuthToken(),
                    2, currentUtcTimestamp, 5 + "",
                    0 + "", getToast(), apiCallback, "ChatList");


        } catch (ParseException e) {
            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }
    }

    public void fetchAfterChat(long lastMsgtimestamp ) {
        patientRepository.fetchChatList(getAuthToken(), channelId,
                1, lastMsgtimestamp + "", 5 + "",
                0 + "", getToast(), apiCallback, "ChatListAfter");
    }

    public void fetchBeforeChat(long firstMsgtimestamp ) {
        patientRepository.fetchHistoryChatList(getAuthToken(),
                2, firstMsgtimestamp + "", 5 + "",
                1+ "", getToast(), apiCallback, "ChatListBefore");
    }


    @Override
    public void onSuccess(String url, Response response) {
        if (!TextUtils.isEmpty(url)) {

            switch (url) {
                case "NewChat":
                    getLoading().setValue(false);

                    ApiResponse<String> apiResponse= (ApiResponse<String>) response.body();
                    Helper.setLog("NewChatResponse", apiResponse.getData());//getData will return channelId


                    if (!TextUtils.isEmpty(apiResponse.getData())) {
                        if(!isFirstMsgSent){
                            isFirstMsgSent=true;
                        }
                        if(TextUtils.isEmpty(channelId)){

                            channelId = apiResponse.getData();


                            setChannelObserver(channelId);
                        }

                       //updateDataRef.child("IsMsgSendFromSupport").setValue(false);
                    }

                    break;



                case "SendMessage":

                    //ApiResponse<Boolean> sentMessageResponse= (ApiResponse<Boolean>) response.body();

                    break;

                case "ChatList":
                    ApiResponse<ChatHistoryData> chatListResponse= (ApiResponse<ChatHistoryData>) response.body();
                    Helper.setLog("chatResponse", chatListResponse.toString());

                    if(!TextUtils.isEmpty(chatListResponse.getData().getResult().getChannel()) && TextUtils.isEmpty(channelId)){
                        channelId =chatListResponse.getData().getResult().getChannel();
                        setChannelObserver(channelId );
                    }
                    remainingHistoryMsgCount=chatListResponse.getData().getCount();
                    ArrayList<Message> msgList = new ArrayList<>();

                    for (int i = 0; i < chatListResponse.getData().getResult().getMsgs().size(); i++) {
                        msgList.add(createMessageObject(chatListResponse.getData().getResult().getMsgs().get(i)));
                    }

                    messageList.setValue(msgList);
                    break;

                case "ChatListAfter":

                    ApiResponse<ChatMessageData> afterChatResponse= (ApiResponse<ChatMessageData>) response.body();
                    Helper.setLog("chatResponse", afterChatResponse.toString());
                    ArrayList<Message> afterChatList = new ArrayList<>();

                    for (int i = 0; i < afterChatResponse.getData().getChatMessageList().size(); i++) {
                        afterChatList.add(createMessageObject(afterChatResponse.getData().getChatMessageList().get(i)));
                    }

                    messageListAfter.setValue(afterChatList);
                    break;

                case "ChatListBefore":

                    ApiResponse<ChatHistoryData> beforeChatResponse= (ApiResponse<ChatHistoryData>) response.body();
                    Helper.setLog("chatResponse", beforeChatResponse.toString());

                    remainingHistoryMsgCount=beforeChatResponse.getData().getCount();
                    ArrayList<Message> beforeChatList = new ArrayList<>();

                    for (int i = 0; i < beforeChatResponse.getData().getResult().getMsgs().size(); i++) {
                        beforeChatList.add(createMessageObject(beforeChatResponse.getData().getResult().getMsgs().get(i)));
                    }

                    messageListBefore.setValue(beforeChatList);
                    break;

                default:
                    break;
            }
        }
    }

    private void setChannelObserver(String channelIdString) {


        Helper.setLog("DataSnapshot", "setChannelObserver");
        channelIdRef=null;
        channelIdRef = database.getReference("ChatInfo").child(channelIdString);



        channelIdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Helper.setLog("DataSnapshot", dataSnapshot.toString());
                if(dataSnapshot !=null && dataSnapshot.getValue()!=null){
                    Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();
                    Boolean supportId = map.get("IsMsgSendFromSupport");

                    if (supportId != null && supportId) {
                        Helper.setLog("IsMsgSendFromSupport", supportId+"");
                        fetchAfterChat(lastMessageTimestamp.getValue());
                        DatabaseReference updateDataRef = FirebaseDatabase.getInstance().getReference("ChatInfo").child(channelIdString);
                        updateDataRef.child("IsMsgSendFromSupport").setValue(false);

                    }
                }else {

                    channelId="";
                    if(isFirstMsgSent){
                        isFirstMsgSent=false;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Helper.setLog("Firebase-DatabaseError", "Failed to read value." + databaseError.getMessage());
                // getToast().setValue(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onError(String url, String errorCode, String errorMessage) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(Response response) {
        getLoading().setValue(false);
    }

    /*@Override
    public void onChatListSuccess(String url, ChatResponse chatResponseObject) {

        if (!TextUtils.isEmpty(url)) {


            switch (url) {

                case "ChatList":
                    ChatResponse chatResponse = chatResponseObject;
                    Helper.setLog("chatResponse", chatResponse.toString());
                    remainingHistoryMsgCount=chatResponse.getData().getCount();
                    ArrayList<Message> msgList = new ArrayList<>();

                    for (int i = 0; i < chatResponse.getData().getChatMessageList().size(); i++) {
                        msgList.add(createMessageObject(chatResponse.getData().getChatMessageList().get(i)));
                    }
                    *//*for (int i = chatResponse.getData().getChatMessageList().size()-1; i>=0; i--) {
                        msgList.add(createMessageObject(chatResponse.getData().getChatMessageList().get(i)));
                    }*//*

                    messageList.setValue(msgList);
                    break;

                case "ChatListAfter":

                    ChatResponse afterChatResponse = chatResponseObject;
                    Helper.setLog("chatResponse", afterChatResponse.toString());
                    ArrayList<Message> afterChatList = new ArrayList<>();

                    for (int i = 0; i < afterChatResponse.getData().getChatMessageList().size(); i++) {
                        afterChatList.add(createMessageObject(afterChatResponse.getData().getChatMessageList().get(i)));
                    }

                    messageListAfter.setValue(afterChatList);
                    break;

                case "ChatListBefore":

                    ChatResponse beforeChatResponse = chatResponseObject;
                    Helper.setLog("chatResponse", beforeChatResponse.toString());

                    remainingHistoryMsgCount=beforeChatResponse.getData().getCount();
                    ArrayList<Message> beforeChatList = new ArrayList<>();

                    for (int i = 0; i < beforeChatResponse.getData().getChatMessageList().size(); i++) {
                        beforeChatList.add(createMessageObject(beforeChatResponse.getData().getChatMessageList().get(i)));
                    }

                    messageListBefore.setValue(beforeChatList);
                    break;

            }
        }

    }*/


    public boolean isNewChat() {
        return isNewChat;
    }

    public void setNewChat(String message) {

        NewChat newChat = new NewChat();
        newChat.setMessage(message);
        getLoading().setValue(true);

        Date utcDate = null;
        try {
            utcDate=Helper.currentlocalDateToUtc();
            Helper.setLog("UTCDate",utcDate.toString());
            Helper.setLog("UTC",utcDate.getTime()+"");
            newChat.setTimestamp(utcDate.getTime());

        } catch (ParseException e) {

            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }

        addSentMsgToAdapter(message,utcDate);

        Helper.setLog("NewChat",Helper.getGsonInstance().toJson(newChat));
        patientRepository.setNewChatRequest(getAuthToken(), newChat, getToast(), apiCallback, "NewChat");
        lastMessageTimestamp.setValue(newChat.getTimestamp());

    }

    public MutableLiveData<String> getTypedMsg() {
        return typedMsg;
    }

    public MutableLiveData<ArrayList<Message>> getMessageList() {
        return messageList;
    }

    public MutableLiveData<ArrayList<Message>> getMessageListAfter() {
        return messageListAfter;
    }

    public Message createMessageObject(ChatMessage chatMessage) {
        Message message = new Message();
        Author user = new Author();
        Date date = null;
        String senderId = "";

        message.setId(chatMessage.getTimeStamp());

        try {

            date=Helper.convertUtcToLocale(chatMessage.getCreatedAt());

        } catch (ParseException e) {
            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }

        if (chatMessage.getFromPatient()) {

            senderId = chatMessage.getPatientId() + "";
            user.setName("");

        } else {

            if (chatMessage.getSupportId() != null) {
                senderId = chatMessage.getSupportId() + "";
                user.setName(chatMessage.getSupportName());
                // name=chatMessage.getSupportName();
            }
        }

        user.setId(senderId);
        user.setOnline(false);


        message.setUser(user);
        message.setText(chatMessage.getMsgBody().trim());
        //message.setId(String.valueOf(timestamp));
        if (date != null) {
            message.setTimestamp(date.getTime());
           /* if(chatMessage.getTimeStamp().equals("0")){

                message.setId(date.getTime()+"");
            }
            else {
                message.setId(chatMessage.getTimeStamp());
            }*/
        }
        message.setCreatedAt(date);
        return message;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public MutableLiveData<Long> getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public int getRemainingHistoryMsgCount() {
        return remainingHistoryMsgCount;
    }

    public void setRemainingHistoryMsgCount(int remainingHistoryMsgCount) {
        this.remainingHistoryMsgCount = remainingHistoryMsgCount;
    }

    public MutableLiveData<Long> getFirstMessageTimestamp() {
        return firstMessageTimestamp;
    }

    public MutableLiveData<ArrayList<Message>> getMessageListBefore() {
        return messageListBefore;
    }

    public boolean isFirstMsgSent() {
        return isFirstMsgSent;
    }

    public void setFirstMsgSent(boolean firstMsgSent) {
        isFirstMsgSent = firstMsgSent;
    }
}
