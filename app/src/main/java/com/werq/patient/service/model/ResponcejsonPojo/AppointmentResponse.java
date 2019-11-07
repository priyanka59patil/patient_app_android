package com.werq.patient.service.model.ResponcejsonPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppointmentResponse implements Serializable, Parcelable {

    @SerializedName("StatusCode")
    private Integer statusCode;
    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private AppointmentData appointmentData;
    public final static Parcelable.Creator<AppointmentResponse> CREATOR = new Creator<AppointmentResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppointmentResponse createFromParcel(Parcel in) {
            return new AppointmentResponse(in);
        }

        public AppointmentResponse[] newArray(int size) {
            return (new AppointmentResponse[size]);
        }

    };

    protected AppointmentResponse(Parcel in) {
        this.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentData = ((AppointmentData) in.readValue((AppointmentData.class.getClassLoader())));
    }

    public AppointmentResponse() {
    }

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

    public AppointmentData getData() {
        return appointmentData;
    }

    public void setData(AppointmentData appointmentData) {
        this.appointmentData = appointmentData;
    }

    @Override
    public String toString() {
        return "AppointmentResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", appointmentData=" + appointmentData +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(message);
        dest.writeValue(appointmentData);
    }

    public int describeContents() {
        return  0;
    }
}
