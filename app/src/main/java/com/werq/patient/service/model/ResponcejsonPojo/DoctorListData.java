package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DoctorListData implements Serializable
{

    @SerializedName("Appointment")
    private List<AppointmentResult> appointment = null;

    @SerializedName("Count")
    private Integer count;

    public List<AppointmentResult> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<AppointmentResult> appointment) {
        this.appointment = appointment;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DoctorListData{" +
                "appointment=" + appointment +
                ", count=" + count +
                '}';
    }
}
