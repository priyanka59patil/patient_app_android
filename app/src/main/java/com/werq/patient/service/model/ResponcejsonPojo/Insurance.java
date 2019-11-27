package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Insurance implements Serializable {

    @SerializedName("PatientId")
    private Integer patientId;
    @SerializedName("InsuranceType")
    private Integer insuranceType;
    @SerializedName("PlanId")
    private String planId;
    @SerializedName("PlanIdType")
    private String planIdType;
    @SerializedName("PlanType")
    private String planType;
    @SerializedName("PlanName")
    private String planName;
    @SerializedName("PlanCompanyId")
    private String planCompanyId;
    @SerializedName("PlanCompanyIdType")
    private String planCompanyIdType;
    @SerializedName("PlanCompanyName")
    private String planCompanyName;
    @SerializedName("PlanCompanyAddress1")
    private String planCompanyAddress1;
    @SerializedName("PlanCompanyAddress2")
    private String planCompanyAddress2;
    @SerializedName("PlanEffectiveDate")
    private String planEffectiveDate;
    @SerializedName("PlanExpirationDate")
    private String planExpirationDate;
    @SerializedName("MemberNumber")
    private String memberNumber;
    @SerializedName("InsuranceGroupId")
    private Integer insuranceGroupId;
    @SerializedName("InsuranceKindId")
    private Integer insuranceKindId;
    @SerializedName("InsuranceKindName")
    private String insuranceKindName;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Integer insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanIdType() {
        return planIdType;
    }

    public void setPlanIdType(String planIdType) {
        this.planIdType = planIdType;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanCompanyId() {
        return planCompanyId;
    }

    public void setPlanCompanyId(String planCompanyId) {
        this.planCompanyId = planCompanyId;
    }

    public String getPlanCompanyIdType() {
        return planCompanyIdType;
    }

    public void setPlanCompanyIdType(String planCompanyIdType) {
        this.planCompanyIdType = planCompanyIdType;
    }

    public String getPlanCompanyName() {
        return planCompanyName;
    }

    public void setPlanCompanyName(String planCompanyName) {
        this.planCompanyName = planCompanyName;
    }

    public String getPlanCompanyAddress1() {
        return planCompanyAddress1;
    }

    public void setPlanCompanyAddress1(String planCompanyAddress1) {
        this.planCompanyAddress1 = planCompanyAddress1;
    }

    public String getPlanCompanyAddress2() {
        return planCompanyAddress2;
    }

    public void setPlanCompanyAddress2(String planCompanyAddress2) {
        this.planCompanyAddress2 = planCompanyAddress2;
    }

    public String getPlanEffectiveDate() {
        return planEffectiveDate;
    }

    public void setPlanEffectiveDate(String planEffectiveDate) {
        this.planEffectiveDate = planEffectiveDate;
    }

    public String getPlanExpirationDate() {
        return planExpirationDate;
    }

    public void setPlanExpirationDate(String planExpirationDate) {
        this.planExpirationDate = planExpirationDate;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Integer getInsuranceGroupId() {
        return insuranceGroupId;
    }

    public void setInsuranceGroupId(Integer insuranceGroupId) {
        this.insuranceGroupId = insuranceGroupId;
    }

    public Integer getInsuranceKindId() {
        return insuranceKindId;
    }

    public void setInsuranceKindId(Integer insuranceKindId) {
        this.insuranceKindId = insuranceKindId;
    }

    public String getInsuranceKindName() {
        return insuranceKindName;
    }

    public void setInsuranceKindName(String insuranceKindName) {
        this.insuranceKindName = insuranceKindName;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "patientId=" + patientId +
                ", insuranceType=" + insuranceType +
                ", planId='" + planId + '\'' +
                ", planIdType='" + planIdType + '\'' +
                ", planType='" + planType + '\'' +
                ", planName='" + planName + '\'' +
                ", planCompanyId='" + planCompanyId + '\'' +
                ", planCompanyIdType='" + planCompanyIdType + '\'' +
                ", planCompanyName='" + planCompanyName + '\'' +
                ", planCompanyAddress1='" + planCompanyAddress1 + '\'' +
                ", planCompanyAddress2='" + planCompanyAddress2 + '\'' +
                ", planEffectiveDate='" + planEffectiveDate + '\'' +
                ", planExpirationDate='" + planExpirationDate + '\'' +
                ", memberNumber='" + memberNumber + '\'' +
                ", insuranceGroupId=" + insuranceGroupId +
                ", insuranceKindId=" + insuranceKindId +
                ", insuranceKindName='" + insuranceKindName + '\'' +
                '}';
    }
}
