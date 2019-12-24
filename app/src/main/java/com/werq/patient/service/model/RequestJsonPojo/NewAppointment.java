package com.werq.patient.service.model.RequestJsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewAppointment implements Serializable {

    @SerializedName("RequestedApptDate")
    private String requestedApptDate;

    @SerializedName("LocationName")
    private String locationName;

    public String getRequestedApptDate() {
        return requestedApptDate;
    }

    public void setRequestedApptDate(String requestedApptDate) {
        this.requestedApptDate = requestedApptDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "NewAppointment{" +
                "requestedApptDate='" + requestedApptDate + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
