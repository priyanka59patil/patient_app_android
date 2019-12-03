package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PastillnessData implements Serializable {

    @SerializedName("HistoryOfPastIllnessId")
    private Integer historyOfPastIllnessId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("HistoryOfPastIllnessList")
    private List<PastillnessHistory> historyOfPastIllnessList = null;

    public Integer getHistoryOfPastIllnessId() {
        return historyOfPastIllnessId;
    }

    public void setHistoryOfPastIllnessId(Integer historyOfPastIllnessId) {
        this.historyOfPastIllnessId = historyOfPastIllnessId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<PastillnessHistory> getHistoryOfPastIllnessList() {
        return historyOfPastIllnessList;
    }

    public void setHistoryOfPastIllnessList(List<PastillnessHistory> historyOfPastIllnessList) {
        this.historyOfPastIllnessList = historyOfPastIllnessList;
    }

    @Override
    public String toString() {
        return "PastillnessData{" +
                "historyOfPastIllnessId=" + historyOfPastIllnessId +
                ", patientRoleId=" + patientRoleId +
                ", historyOfPastIllnessList=" + historyOfPastIllnessList +
                '}';
    }
}
