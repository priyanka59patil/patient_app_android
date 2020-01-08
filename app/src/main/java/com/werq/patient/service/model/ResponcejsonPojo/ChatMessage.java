package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    @SerializedName("PatientName")
    private String patientName;

    @SerializedName("SupportName")
    private String supportName;

    @SerializedName("ID")
    private Integer iD;

    @SerializedName("MsgBody")
    private String msgBody;

    @SerializedName("ChannelId")
    private Integer channelId;

    @SerializedName("PatientId")
    private Integer patientId;

    @SerializedName("SupportId")
    private Integer supportId;

    @SerializedName("FromPatient")
    private Boolean fromPatient;

    @SerializedName("Status")
    private Integer status;

    @SerializedName("TimeStamp")
    private Long timeStamp;

    @SerializedName("CreatedAt")
    private String createdAt;

    @SerializedName("UpdatedAt")
    private String updatedAt;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getSupportId() {
        return supportId;
    }

    public void setSupportId(Integer supportId) {
        this.supportId = supportId;
    }

    public Boolean getFromPatient() {
        return fromPatient;
    }

    public void setFromPatient(Boolean fromPatient) {
        this.fromPatient = fromPatient;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "patientName='" + patientName + '\'' +
                ", supportName='" + supportName + '\'' +
                ", iD=" + iD +
                ", msgBody='" + msgBody + '\'' +
                ", channelId=" + channelId +
                ", patientId=" + patientId +
                ", supportId=" + supportId +
                ", fromPatient=" + fromPatient +
                ", status=" + status +
                ", timeStamp=" + timeStamp +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
