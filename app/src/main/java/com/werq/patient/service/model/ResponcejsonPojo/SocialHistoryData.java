package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SocialHistoryData implements Serializable {
    
    @SerializedName("SocialHistoryId")
    private Integer socialHistoryId;
    @SerializedName("PatientRoleId")
    private Integer patientRoleId;
    @SerializedName("SocialHistoryList")
    private List<SocialHistory> socialHistoryList = null;

    public Integer getSocialHistoryId() {
        return socialHistoryId;
    }

    public void setSocialHistoryId(Integer socialHistoryId) {
        this.socialHistoryId = socialHistoryId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<SocialHistory> getSocialHistoryList() {
        return socialHistoryList;
    }

    public void setSocialHistoryList(List<SocialHistory> socialHistoryList) {
        this.socialHistoryList = socialHistoryList;
    }

    @Override
    public String toString() {
        return "SocialHistoryData{" +
                "socialHistoryId=" + socialHistoryId +
                ", patientRoleId=" + patientRoleId +
                ", socialHistoryList=" + socialHistoryList +
                '}';
    }
}
