package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Interfaces.ChatListApiCall;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.NewChat;
import com.werq.patient.service.model.ResponcejsonPojo.NewChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SentMessageResponse;
import com.werq.patient.service.model.chat.Author;
import com.werq.patient.service.model.chat.Message;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

public class ChatFragmentViewModel extends BaseViewModel implements ChatListApiCall {

    CompositeDisposable disposable;
    PatientRepository patientRepository;
    ApiResponce apiResponce = this;
    ChatListApiCall apiResponceChatList = this;
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

    public ChatFragmentViewModel() {

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

    public void fetchInitialChat() {

        try {
            String currentUtcTimestamp=Helper.currentlocalDateToUtc().getTime() + "";
            patientRepository.fetchChatList(Helper.autoken, channelId,
                    2, currentUtcTimestamp, 5 + "",
                    0 + "", getToast(), apiResponceChatList, "ChatList");


        } catch (ParseException e) {
            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }
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

        Message chatMessage = new Message();
        Author user = new Author();

        if(utcDate!=null){
            chatMessage.setId(sendMessage.getTimeStamp()+"");
            chatMessage.setTimestamp(sendMessage.getTimeStamp());
            chatMessage.setCreatedAt(utcDate);
        }

        user.setId(SessionManager.getUserId());
        user.setOnline(false);
        //user.setName(name);
        chatMessage.setUser(user);
        chatMessage.setText(message);

        ArrayList<Message> afterChatList = new ArrayList<>();
        afterChatList.add(chatMessage);
        messageListAfter.setValue(afterChatList);

        patientRepository.sendMessageToServer(Helper.autoken, sendMessage, getToast(), apiResponce, "SendMessage");
        Helper.setLog("last msg timestamp msg sent",sendMessage.getTimeStamp()+"");
        lastMessageTimestamp.setValue(sendMessage.getTimeStamp());
    }


    public void fetchAfterChat(long lastMsgtimestamp ) {
        patientRepository.fetchChatList(Helper.autoken, channelId,
                1, lastMsgtimestamp + "", 5 + "",
                0 + "", getToast(), apiResponceChatList, "ChatListAfter");
    }

    public void fetchBeforeChat(long firstMsgtimestamp ) {
        patientRepository.fetchChatList(Helper.autoken, channelId,
                2, firstMsgtimestamp + "", 5 + "",
                1+ "", getToast(), apiResponceChatList, "ChatListBefore");
    }


    @Override
    public void onSuccess(String url, String responseJson) {


        if (!TextUtils.isEmpty(url)) {

            switch (url) {
                case "NewChat":
                    getLoading().setValue(false);
                    NewChatResponse newChatResponse = Helper.getGsonInstance().fromJson(responseJson, NewChatResponse.class);
                    Helper.setLog("NewChatResponse", newChatResponse.toString());

                    if (!TextUtils.isEmpty(newChatResponse.getChannelId())) {

                        channelIdRef = database.getReference(newChatResponse.getChannelId());
                        channelId = newChatResponse.getChannelId();
                        fetchInitialChat();

                        DatabaseReference updateDataRef = FirebaseDatabase.getInstance().getReference(channelId);
                        updateDataRef.child("IsMsgSendFromSupport").setValue(false);
                    }

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

                                    DatabaseReference updateDataRef = FirebaseDatabase.getInstance().getReference(channelId);
                                    updateDataRef.child("IsMsgSendFromSupport").setValue(false);

                                }
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Helper.setLog("Firebase-DatabaseError", "Failed to read value." + databaseError.getMessage());
                            // getToast().setValue(databaseError.getMessage());
                        }
                    });

                    break;



                case "SendMessage":

                    SentMessageResponse sentMessageResponse = Helper.getGsonInstance().fromJson(responseJson, SentMessageResponse.class);

                    if (sentMessageResponse != null && sentMessageResponse.getStatusCode() == 200) {
                        fetchAfterChat(lastMessageTimestamp.getValue());
                    }

                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public void onError(String url, String errorCode, String errorMessage) {
        getLoading().setValue(false);
    }

    @Override
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
                    /*for (int i = chatResponse.getData().getChatMessageList().size()-1; i>=0; i--) {
                        msgList.add(createMessageObject(chatResponse.getData().getChatMessageList().get(i)));
                    }*/

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

    }

    @Override
    public void onChatListError(String url, String errorCode, String errorMessage) {

    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
    }

    public boolean isNewChat() {
        return isNewChat;
    }

    public void setNewChat(String message) {

        NewChat newChat = new NewChat();
        newChat.setMessage(message);
        getLoading().setValue(true);
        patientRepository.setNewChatRequest(Helper.autoken, newChat, getToast(), apiResponce, "NewChat");

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
}
