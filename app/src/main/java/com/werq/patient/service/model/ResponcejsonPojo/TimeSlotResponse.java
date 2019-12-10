package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimeSlotResponse implements Serializable {

    @SerializedName("StatusCode")
    private Integer statusCode;

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private TimeSlotData data;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TimeSlotData getData() {
        return data;
    }

    public void setData(TimeSlotData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TimeSlotResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
