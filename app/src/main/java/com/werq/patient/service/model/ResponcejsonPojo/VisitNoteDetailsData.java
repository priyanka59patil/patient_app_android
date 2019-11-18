package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VisitNoteDetailsData implements Serializable {

    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("Result")
    @Expose
    private VisitNoteResult visitNoteResult;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public VisitNoteResult getVisitNoteResult() {
        return visitNoteResult;
    }

    public void setVisitNoteResult(VisitNoteResult visitNoteResult) {
        this.visitNoteResult = visitNoteResult;
    }

    @Override
    public String toString() {
        return "VisitNoteDetailsData{" +
                "count=" + count +
                ", visitNoteResult=" + visitNoteResult +
                '}';
    }
}
