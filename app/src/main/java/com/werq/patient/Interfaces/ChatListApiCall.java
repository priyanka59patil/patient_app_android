package com.werq.patient.Interfaces;

import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;

import retrofit2.Response;

public interface ChatListApiCall {

    /*void onChatListSuccess(String url, ChatResponse chatResponse);
    void onChatListError(String url, String errorCode, String errorMessage);
    void onTokenRefersh(String responseJson);*/

    void onChatListSuccess(String url, Response response);
    void onChatListError(String url, String errorCode, String errorMessage);
    void onTokenRefersh(String responseJson);
}
