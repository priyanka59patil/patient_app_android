package com.werq.patient.service.model.RequestJsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewChat implements Serializable {

    @SerializedName("Message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NewChat{" +
                "message='" + message + '\'' +
                '}';
    }
}
