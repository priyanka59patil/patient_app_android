package com.werq.patient.service.model.RequestJsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SendMessage implements Serializable {

    @SerializedName("Channel")
    private String channel;

    @SerializedName("Message")
    private String message;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "channel='" + channel + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
