package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DoctorData implements Serializable {

    @SerializedName("Count")
    private Integer count;

    @SerializedName("Result")
    private List<DoctorTeamResult> result = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<DoctorTeamResult> getResult() {
        return result;
    }

    public void setResult(List<DoctorTeamResult> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DoctorData{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }
}
