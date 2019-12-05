package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DoctorListResponse implements Serializable {

    @SerializedName("StatusCode")
    private Integer statusCode;

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private DoctorListData data;


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

    public DoctorListData getData() {
        return data;
    }

    public void setData(DoctorListData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoctorListResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
