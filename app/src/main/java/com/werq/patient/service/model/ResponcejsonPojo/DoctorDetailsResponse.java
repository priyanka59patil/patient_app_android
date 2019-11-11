package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DoctorDetailsResponse implements Serializable {

    @SerializedName("StatusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private DoctorDetailsData data;

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

    public DoctorDetailsData getData() {
        return data;
    }

    public void setData(DoctorDetailsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoctorDetailsResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
