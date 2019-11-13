package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AttachmentData implements Serializable {

    @SerializedName("Count")
    private Integer count;

    @SerializedName("Result")
    private List<AttachmentResult> result = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AttachmentResult> getResult() {
        return result;
    }

    public void setResult(List<AttachmentResult> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AttachmentData{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }
}
