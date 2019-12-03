package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Problem implements Serializable {

    @SerializedName("ProblemDataId")
    private Integer problemDataId;
    @SerializedName("ProblemName")
    private String problemName;
    @SerializedName("ProblemCode")
    private String problemCode;
    @SerializedName("ProblemType")
    private String problemType;
    @SerializedName("ProblemStatus")
    private String problemStatus;
    @SerializedName("ProblemDiagnosedDate")
    private String problemDiagnosedDate;
    @SerializedName("ProblemEndDate")
    private String problemEndDate;

    public Integer getProblemDataId() {
        return problemDataId;
    }

    public void setProblemDataId(Integer problemDataId) {
        this.problemDataId = problemDataId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(String problemStatus) {
        this.problemStatus = problemStatus;
    }

    public String getProblemDiagnosedDate() {
        return problemDiagnosedDate;
    }

    public void setProblemDiagnosedDate(String problemDiagnosedDate) {
        this.problemDiagnosedDate = problemDiagnosedDate;
    }

    public String getProblemEndDate() {
        return problemEndDate;
    }

    public void setProblemEndDate(String problemEndDate) {
        this.problemEndDate = problemEndDate;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problemDataId=" + problemDataId +
                ", problemName='" + problemName + '\'' +
                ", problemCode='" + problemCode + '\'' +
                ", problemType='" + problemType + '\'' +
                ", problemStatus='" + problemStatus + '\'' +
                ", problemDiagnosedDate='" + problemDiagnosedDate + '\'' +
                ", problemEndDate='" + problemEndDate + '\'' +
                '}';
    }
}
