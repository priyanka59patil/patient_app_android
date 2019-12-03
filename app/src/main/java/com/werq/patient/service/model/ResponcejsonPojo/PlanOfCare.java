package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlanOfCare implements Serializable {

    @SerializedName("PlanOfCareDataId")
    private Integer planOfCareDataId;

    @SerializedName("Code")
    private String code;

    @SerializedName("Details")
    private String details;

    @SerializedName("Instructions")
    private String instructions;

    public Integer getPlanOfCareDataId() {
        return planOfCareDataId;
    }

    public void setPlanOfCareDataId(Integer planOfCareDataId) {
        this.planOfCareDataId = planOfCareDataId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "PlanOfCare{" +
                "planOfCareDataId=" + planOfCareDataId +
                ", code='" + code + '\'' +
                ", details='" + details + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
