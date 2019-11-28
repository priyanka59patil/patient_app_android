package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MedicationData implements Serializable {

    @SerializedName("MedicationId")
    private Integer medicationId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("MedicationDataList")
    private List<MedicationDatum> medicationDataList = null;

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<MedicationDatum> getMedicationDataList() {
        return medicationDataList;
    }

    public void setMedicationDataList(List<MedicationDatum> medicationDataList) {
        this.medicationDataList = medicationDataList;
    }

    @Override
    public String toString() {
        return "MedicationData{" +
                "medicationId=" + medicationId +
                ", patientRoleId=" + patientRoleId +
                ", medicationDataList=" + medicationDataList +
                '}';
    }
}
