package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VisitNoteResult implements Serializable {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("AppointmentId")
    @Expose
    private Integer appointmentId;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("CreatedByUser")
    @Expose
    private VisitNoteCreatedByUser visitNoteCreatedByUser;
    @SerializedName("Attachment")
    @Expose
    private List<AttachmentResult> attachement = null;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public VisitNoteCreatedByUser getVisitNoteCreatedByUser() {
        return visitNoteCreatedByUser;
    }

    public void setVisitNoteCreatedByUser(VisitNoteCreatedByUser visitNoteCreatedByUser) {
        this.visitNoteCreatedByUser = visitNoteCreatedByUser;
    }

    public List<AttachmentResult> getAttachement() {
        return attachement;
    }

    public void setAttachement(List<AttachmentResult> attachement) {
        this.attachement = attachement;
    }

    @Override
    public String toString() {
        return "VisitNoteResult{" +
                "iD=" + iD +
                ", appointmentId=" + appointmentId +
                ", note='" + note + '\'' +
                ", createdBy=" + createdBy +
                ", visitNoteCreatedByUser=" + visitNoteCreatedByUser +
                ", attachement=" + attachement +
                '}';
    }
}
