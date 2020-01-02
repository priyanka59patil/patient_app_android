package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DoctorDetailsData implements Serializable {

    @SerializedName("Doctor")
    private Doctor doctor;
    @SerializedName("Locations")
    private List<Location> locations = null;
    @SerializedName("Coworker")
    @Expose
    private List<Doctor> coworker = null;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Doctor> getCoworker() {
        return coworker;
    }

    public void setCoworker(List<Doctor> coworker) {
        this.coworker = coworker;
    }

    @Override
    public String toString() {
        return "DoctorDetailsData{" +
                "doctor=" + doctor +
                ", locations=" + locations +
                ", coworker=" + coworker +
                '}';
    }
}
