package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChatMessageData implements Serializable {

    @SerializedName("Count")
    private Integer count;

    @SerializedName("Result")
    private List<ChatMessage> chatMessageList = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    @Override
    public String toString() {
        return "ChatMessageData{" +
                "count=" + count +
                ", chatMessageList=" + chatMessageList +
                '}';
    }
}
