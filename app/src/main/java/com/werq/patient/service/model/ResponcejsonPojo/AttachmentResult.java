package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttachmentResult implements Serializable {


    @SerializedName("ID")
    private Integer iD;

    @SerializedName("VisitNoteId")
    private Integer visitNoteId;

    @SerializedName("AppointmentId")
    private Integer appointmentId;

    @SerializedName("ReferralId")
    private Integer referralId;

    @SerializedName("CreatedBy")
    private Integer createdBy;

    @SerializedName("CreatedByUser")
    private CreatedByUser createdByUser;

    @SerializedName("FileName")
    private String fileName;

    @SerializedName("FileUrl")
    private String fileUrl;

    @SerializedName("FileType")
    private String fileType;

    @SerializedName("ResizeURL")
    private String resizeURL;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getVisitNoteId() {
        return visitNoteId;
    }

    public void setVisitNoteId(Integer visitNoteId) {
        this.visitNoteId = visitNoteId;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public CreatedByUser getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(CreatedByUser createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getResizeURL() {
        return resizeURL;
    }

    public void setResizeURL(String resizeURL) {
        this.resizeURL = resizeURL;
    }

    public Integer getReferralId() {
        return referralId;
    }

    public void setReferralId(Integer referralId) {
        this.referralId = referralId;
    }

    @Override
    public String toString() {
        return "AttachmentResult{" +
                "iD=" + iD +
                ", visitNoteId=" + visitNoteId +
                ", appointmentId=" + appointmentId +
                ", referralId=" + referralId +
                ", createdBy=" + createdBy +
                ", createdByUser=" + createdByUser +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                ", resizeURL='" + resizeURL + '\'' +
                '}';
    }
}
