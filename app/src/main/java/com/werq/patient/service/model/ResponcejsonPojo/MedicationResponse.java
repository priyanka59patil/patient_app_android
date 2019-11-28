package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MedicationResponse implements Serializable {

    @SerializedName("StatusCode")
    private Integer statusCode;

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private MedicationData data;

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

    public MedicationData getData() {
        return data;
    }

    public void setData(MedicationData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MedicationResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
