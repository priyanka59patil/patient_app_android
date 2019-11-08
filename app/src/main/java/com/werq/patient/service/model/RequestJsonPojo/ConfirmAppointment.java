package com.werq.patient.service.model.RequestJsonPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfirmAppointment implements Serializable {

    @SerializedName("ID")
    @Expose
    private Integer ID;

    @SerializedName("ConfirmByPatient")
    @Expose
    private String confirmByPatient;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getConfirmByPatient() {
        return confirmByPatient;
    }

    public void setConfirmByPatient(String confirmByPatient) {
        this.confirmByPatient = confirmByPatient;
    }
}
