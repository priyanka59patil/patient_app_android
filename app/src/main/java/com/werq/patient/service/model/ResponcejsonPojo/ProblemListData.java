package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProblemListData implements Serializable {

    @SerializedName("ProblemId")
    private Integer problemId;
    @SerializedName("PatientRoleId")
    private Integer patientRoleId;
    @SerializedName("ProblemsList")
    private List<Problem> problemsList = null;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<Problem> getProblemsList() {
        return problemsList;
    }

    public void setProblemsList(List<Problem> problemsList) {
        this.problemsList = problemsList;
    }

    @Override
    public String toString() {
        return "ProblemListData{" +
                "problemId=" + problemId +
                ", patientRoleId=" + patientRoleId +
                ", problemsList=" + problemsList +
                '}';
    }
}
