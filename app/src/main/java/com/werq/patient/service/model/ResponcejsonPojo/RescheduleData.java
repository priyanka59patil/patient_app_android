package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RescheduleData implements Serializable {

    @SerializedName("Item2")
    private RescheduleItem item2;

    public RescheduleItem getItem2() {
        return item2;
    }

    public void setItem2(RescheduleItem item2) {
        this.item2 = item2;
    }

    @Override
    public String toString() {
        return "RescheduleData{" +
                "item2=" + item2 +
                '}';
    }
}
