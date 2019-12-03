package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SocialHistory implements Serializable {


    @SerializedName("SocialHistoryDataId")
    private Integer socialHistoryDataId;
    @SerializedName("Code")
    private String code;
    @SerializedName("Activity")
    private String activity;
    @SerializedName("StartDate")
    private String startDate;
    @SerializedName("EndDate")
    private String endDate;

    public Integer getSocialHistoryDataId() {
        return socialHistoryDataId;
    }

    public void setSocialHistoryDataId(Integer socialHistoryDataId) {
        this.socialHistoryDataId = socialHistoryDataId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SocialHistory{" +
                " activity='" + activity + '\'' +
                ",code='" + code + '\'' +
                '}';
    }
}
