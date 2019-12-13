package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RescheduleItem implements Serializable {

    @SerializedName("Appointment")
    private AppointmentResult appointment;
    @SerializedName("VisitNoteAttachment")
    private List<VisitNoteAttachment> visitNoteAttachment = null;

    public AppointmentResult getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentResult appointment) {
        this.appointment = appointment;
    }

    public List<VisitNoteAttachment> getVisitNoteAttachment() {
        return visitNoteAttachment;
    }

    public void setVisitNoteAttachment(List<VisitNoteAttachment> visitNoteAttachment) {
        this.visitNoteAttachment = visitNoteAttachment;
    }

    @Override
    public String toString() {
        return "RescheduleItem{" +
                "appointment=" + appointment +
                ", visitNoteAttachment=" + visitNoteAttachment +
                '}';
    }
}
