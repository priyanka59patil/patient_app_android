package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChatHistoryResult implements Serializable {

    @SerializedName("Channel")
    private String channel;

    @SerializedName("Msgs")
    private List<ChatMessage> msgs = null;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<ChatMessage> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<ChatMessage> msgs) {
        this.msgs = msgs;
    }

    @Override
    public String toString() {
        return "ChatHistoryResult{" +
                "channel='" + channel + '\'' +
                ", msgs=" + msgs +
                '}';
    }
}
