package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Assessment implements Serializable {

    @SerializedName("AssessmentId")
    private Integer assessmentId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("Assessment")
    private String assessment;

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", patientRoleId=" + patientRoleId +
                ", assessment='" + assessment + '\'' +
                '}';
    }
}
