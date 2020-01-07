package com.werq.patient.service.model;

public class FirebaseChatNode {

    Boolean patientId;
    Boolean supportId;

    public Boolean getPatientId() {
        return patientId;
    }

    public void setPatientId(Boolean patientId) {
        this.patientId = patientId;
    }

    public Boolean getSupportId() {
        return supportId;
    }

    public void setSupportId(Boolean supportId) {
        this.supportId = supportId;
    }

    @Override
    public String toString() {
        return "FirebaseChatNode{" +
                "patientId=" + patientId +
                ", supportId=" + supportId +
                '}';
    }
}
