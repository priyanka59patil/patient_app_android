package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllergyData implements Serializable {

    @SerializedName("AllergyId")
    private Integer allergyId;
    @SerializedName("PatientRoleId")
    private Integer patientRoleId;
    @SerializedName("AllergyList")
    private List<Allergy> allergyList = null;

    public Integer getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Integer allergyId) {
        this.allergyId = allergyId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<Allergy> getAllergyList() {
        return allergyList;
    }

    public void setAllergyList(List<Allergy> allergyList) {
        this.allergyList = allergyList;
    }

    @Override
    public String toString() {
        return "AllergyData{" +
                "allergyId=" + allergyId +
                ", patientRoleId=" + patientRoleId +
                ", allergyList=" + allergyList +
                '}';
    }
}
