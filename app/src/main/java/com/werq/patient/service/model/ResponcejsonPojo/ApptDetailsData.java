package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ApptDetailsData implements Serializable {

    @SerializedName("Appointment")
    private AppointmentResult appointmentResult;

    @SerializedName("VisitNoteAttachment")
    private List<VisitNoteAttachment> visitNoteAttachment = null;


    public AppointmentResult getAppointment() {
        return appointmentResult;
    }

    public void setAppointment(AppointmentResult appointmentResult) {
        this.appointmentResult = appointmentResult;
    }

    public List<VisitNoteAttachment> getVisitNoteAttachment() {
        return visitNoteAttachment;
    }

    public void setVisitNoteAttachment(List<VisitNoteAttachment> visitNoteAttachment) {
        this.visitNoteAttachment = visitNoteAttachment;
    }

    @Override
    public String toString() {
        return "ApptDetailsData{" +
                "appointmentResult=" + appointmentResult +
                ", visitNoteAttachment=" + visitNoteAttachment +
                '}';
    }
}
