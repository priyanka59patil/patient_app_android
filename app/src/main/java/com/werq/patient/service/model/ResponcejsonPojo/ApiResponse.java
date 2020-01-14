package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

    @SerializedName("StatusCode")
    private Integer StatusCode;

    @SerializedName("Message")
    private String Message;

    @SerializedName("Data")
    private T Data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Integer getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Integer statusCode) {
        StatusCode = statusCode;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "Message='" + Message + '\'' +
                ", StatusCode='" + StatusCode + '\'' +
                ", Data=" + Data +
                '}';
    }
}
