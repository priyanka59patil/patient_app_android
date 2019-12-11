package com.werq.patient.service.model.RequestJsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RescheduleAppointment implements Serializable {

    @SerializedName("ID")
    private String appointmentId;

    @SerializedName("RescheduleApptReqDate")
    private String rescheduleApptReqDate;

    @SerializedName("RescheduleApptRequestReason")
    private String rescheduleApptRequestReason;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getRescheduleApptReqDate() {
        return rescheduleApptReqDate;
    }

    public void setRescheduleApptReqDate(String rescheduleApptReqDate) {
        this.rescheduleApptReqDate = rescheduleApptReqDate;
    }

    public String getRescheduleApptRequestReason() {
        return rescheduleApptRequestReason;
    }

    public void setRescheduleApptRequestReason(String rescheduleApptRequestReason) {
        this.rescheduleApptRequestReason = rescheduleApptRequestReason;
    }


    @Override
    public String toString() {
        return "RescheduleAppointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", rescheduleApptReqDate='" + rescheduleApptReqDate + '\'' +
                ", rescheduleApptRequestReason='" + rescheduleApptRequestReason + '\'' +
                '}';
    }
}
