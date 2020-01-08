package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.FirebaseChatNode;
import com.werq.patient.service.model.RequestJsonPojo.SendMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatMessage;
import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.NewChat;
import com.werq.patient.service.model.ResponcejsonPojo.NewChatResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SentMessageResponse;
import com.werq.patient.service.model.chat.Author;
import com.werq.patient.service.model.chat.Message;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

public class ChatFragmentViewModel extends BaseViewModel {

    CompositeDisposable disposable;
    PatientRepository patientRepository;
    ApiResponce apiResponce = this;
    boolean isNewChat = false;
    MutableLiveData<String> typedMsg;
    MutableLiveData<ArrayList<Message>> messageList;
    MutableLiveData<ArrayList<Message>> messageListAfter;
    MutableLiveData<Long> lastMessageTimestamp;
    FirebaseDatabase database;
    DatabaseReference channelIdRef;
    SessionManager sessionManager;
    String channelId;

    public ChatFragmentViewModel(Context mContext) {

        database = FirebaseDatabase.getInstance();
        patientRepository = new PatientRepository();
        isNewChat = true;
        typedMsg = new MutableLiveData<>();
        messageList = new MutableLiveData<>();
        messageListAfter = new MutableLiveData<>();
        lastMessageTimestamp = new MutableLiveData<>();
        lastMessageTimestamp.setValue(0L);

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

        patientRepository.fetchChatList(Helper.autoken, channelId,
                2, new Date().getTime() + "", 25 + "",
                0 + "", getToast(), apiResponce, "ChatList");
    }

    public void sendMessageToServer(String message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChannel(channelId);
        sendMessage.setMessage(message);
        try {
            Helper.setLog("UTCDate",Helper.currentlocalDateToUtc().toString());
            sendMessage.setTimeStamp(Helper.currentlocalDateToUtc().getTime());


        } catch (ParseException e) {

            Helper.setExceptionLog("ParseException",e);
            e.printStackTrace();
        }
        patientRepository.sendMessageToServer(Helper.autoken, sendMessage, getToast(), apiResponce, "SendMessage");
        lastMessageTimestamp.setValue(new Date().getTime());
    }


    public void fetchAfterChat(long timestamp) {
        patientRepository.fetchChatList(Helper.autoken, channelId,
                1, timestamp + "", 25 + "",
                0 + "", getToast(), apiResponce, "ChatListAfter");
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
                    }

                    channelIdRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.

                            Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();
                            Boolean supportId = map.get("IsMsgSendFromSupport");

                            if (supportId != null && supportId) {

                                Helper.setLog("timestamp", "" + new Date().getTime());

                                fetchAfterChat(lastMessageTimestamp.getValue());

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Helper.setLog("Firebase-DatabaseError", "Failed to read value." + databaseError.getMessage());
                            // getToast().setValue(databaseError.getMessage());
                        }
                    });

                    break;

                case "ChatList":

                    ChatResponse chatResponse = Helper.getGsonInstance().fromJson(responseJson, ChatResponse.class);
                    Helper.setLog("chatResponse", chatResponse.toString());
                    ArrayList<Message> msgList = new ArrayList<>();

                    for (int i = 0; i < chatResponse.getData().getChatMessageList().size(); i++) {
                        msgList.add(createMessageObject(chatResponse.getData().getChatMessageList().get(i)));
                    }

                    messageList.setValue(msgList);
                    break;

                case "ChatListAfter":

                    ChatResponse afterChatResponse = Helper.getGsonInstance().fromJson(responseJson, ChatResponse.class);
                    Helper.setLog("chatResponse", afterChatResponse.toString());
                    ArrayList<Message> afterChatList = new ArrayList<>();

                    for (int i = 0; i < afterChatResponse.getData().getChatMessageList().size(); i++) {
                        afterChatList.add(createMessageObject(afterChatResponse.getData().getChatMessageList().get(i)));
                    }

                    messageListAfter.setValue(afterChatList);
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

        date=new Timestamp(chatMessage.getTimeStamp());

        if (chatMessage.getFromPatient()) {

            senderId = chatMessage.getPatientId() + "";

        } else {

            if (chatMessage.getSupportId() != null) {
                senderId = chatMessage.getSupportId() + "";
                // name=chatMessage.getSupportName();
            }
        }

        user.setId(senderId);
        user.setOnline(false);
        //user.setName(name);

        message.setUser(user);
        message.setText(chatMessage.getMsgBody());
        //message.setId(String.valueOf(timestamp));
        if (date != null) {
            message.setTimestamp(date.getTime());
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
}
