package com.werq.patient.Interfaces;

import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;

public interface ChatListApiCall {

    void onChatListSuccess(String url, ChatResponse chatResponse);
    void onChatListError(String url, String errorCode, String errorMessage);
    void onTokenRefersh(String responseJson);


}
