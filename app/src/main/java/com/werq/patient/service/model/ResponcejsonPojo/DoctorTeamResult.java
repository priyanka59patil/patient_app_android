package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DoctorTeamResult  implements Serializable {


    @SerializedName("Location")
    private Location location;

    @SerializedName("Doctors")
    private List<Doctor> doctors = null;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "DoctorTeamResult{" +
                "location=" + location +
                ", doctors=" + doctors +
                '}';
    }
}
