package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProcedureHistoryData implements Serializable {

    @SerializedName("HistoryOfProcedureId")
    private Integer historyOfProcedureId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("HistoryOfProcedureList")
    private List<HistoryOfProcedure> historyOfProcedureList = null;

    public Integer getHistoryOfProcedureId() {
        return historyOfProcedureId;
    }

    public void setHistoryOfProcedureId(Integer historyOfProcedureId) {
        this.historyOfProcedureId = historyOfProcedureId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<HistoryOfProcedure> getHistoryOfProcedureList() {
        return historyOfProcedureList;
    }

    public void setHistoryOfProcedureList(List<HistoryOfProcedure> historyOfProcedureList) {
        this.historyOfProcedureList = historyOfProcedureList;
    }

    @Override
    public String toString() {
        return "ProcedureHistoryData{" +
                "historyOfProcedureId=" + historyOfProcedureId +
                ", patientRoleId=" + patientRoleId +
                ", historyOfProcedureList=" + historyOfProcedureList +
                '}';
    }
}
