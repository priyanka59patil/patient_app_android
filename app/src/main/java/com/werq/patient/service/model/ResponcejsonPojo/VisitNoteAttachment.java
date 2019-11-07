
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitNoteAttachment implements Serializable
{

    @SerializedName("ID")
    private Integer iD;

    @SerializedName("AppointmentId")
    private Integer appointmentId;

    @SerializedName("Note")
    private String note;

    @SerializedName("CreatedBy")
    private Integer createdBy;

    @SerializedName("Attachement")
    private List<Attachement> attachement = null;

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

    public List<Attachement> getAttachement() {
        return attachement;
    }

    public void setAttachement(List<Attachement> attachement) {
        this.attachement = attachement;
    }

    @Override
    public String toString() {
        return "VisitNoteAttachment{" +
                "iD=" + iD +
                ", appointmentId=" + appointmentId +
                ", note='" + note + '\'' +
                ", createdBy=" + createdBy +
                ", attachement=" + attachement +
                '}';
    }
}
