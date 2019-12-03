package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PlanOfCareData implements Serializable {
    @SerializedName("PlanOfCareId")
    private Integer planOfCareId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("PlanOfCareList")
    private List<PlanOfCare> planOfCareList = null;

    public Integer getPlanOfCareId() {
        return planOfCareId;
    }

    public void setPlanOfCareId(Integer planOfCareId) {
        this.planOfCareId = planOfCareId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<PlanOfCare> getPlanOfCareList() {
        return planOfCareList;
    }

    public void setPlanOfCareList(List<PlanOfCare> planOfCareList) {
        this.planOfCareList = planOfCareList;
    }

    @Override
    public String toString() {
        return "PlanOfCareData{" +
                "planOfCareId=" + planOfCareId +
                ", patientRoleId=" + patientRoleId +
                ", planOfCareList=" + planOfCareList +
                '}';
    }
}
