package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PastillnessHistory implements Serializable {

    @SerializedName("HistoryOfPastIllnessId")
    private Integer historyOfPastIllnessId;

    @SerializedName("Problem")
    private String problem;

    @SerializedName("Code")
    private String code;

    @SerializedName("Type")
    private String type;

    @SerializedName("Status")
    private String status;

    @SerializedName("DiagnosedDate")
    private String diagnosedDate;

    @SerializedName("EndDate")
    private String endDate;

    public Integer getHistoryOfPastIllnessId() {
        return historyOfPastIllnessId;
    }

    public void setHistoryOfPastIllnessId(Integer historyOfPastIllnessId) {
        this.historyOfPastIllnessId = historyOfPastIllnessId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnosedDate() {
        return diagnosedDate;
    }

    public void setDiagnosedDate(String diagnosedDate) {
        this.diagnosedDate = diagnosedDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "PastillnessHistory{" +
                ", problem='" + problem + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
