package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EncounterData implements Serializable {

    @SerializedName("EncountersId")
    private Integer encountersId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("EncountersList")
    private List<Encounter> encountersList = null;

    public Integer getEncountersId() {
        return encountersId;
    }

    public void setEncountersId(Integer encountersId) {
        this.encountersId = encountersId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<Encounter> getEncountersList() {
        return encountersList;
    }

    public void setEncountersList(List<Encounter> encountersList) {
        this.encountersList = encountersList;
    }

    @Override
    public String toString() {
        return "EncounterData{" +
                "encountersId=" + encountersId +
                ", patientRoleId=" + patientRoleId +
                ", encountersList=" + encountersList +
                '}';
    }
}
