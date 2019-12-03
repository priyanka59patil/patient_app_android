package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProblemListResponse implements Serializable
{

    @SerializedName("StatusCode")
    private Integer statusCode;

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private ProblemListData data;

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

    public ProblemListData getData() {
        return data;
    }

    public void setData(ProblemListData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProblemListResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
