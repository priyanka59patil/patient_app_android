package com.werq.patient.service.model.ResponcejsonPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppointmentResult implements Serializable
{

    @SerializedName("ID")
    private String iD;

    @SerializedName("PatientId")
    private String patientId;

    @SerializedName("DoctorId")
    private String doctorId;

    @SerializedName("LocationId")
    private String locationId;

    @SerializedName("AppintmentDate")
    private String appintmentDate;

    @SerializedName("AppointmentStatus")
    private String appointmentStatus;

    @SerializedName("ConfirmByPatient")
    private Boolean confirmByPatient;

    @SerializedName("Doctor")
    private Doctor doctor;

    @SerializedName("Location")
    private Location location;


    public AppointmentResult() {
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getAppintmentDate() {
        return appintmentDate;
    }

    public void setAppintmentDate(String appintmentDate) {
        this.appintmentDate = appintmentDate;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Boolean getConfirmByPatient() {
        return confirmByPatient;
    }

    public void setConfirmByPatient(Boolean confirmByPatient) {
        this.confirmByPatient = confirmByPatient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "AppointmentResult{" +
                "iD=" + iD +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", locationId=" + locationId +
                ", appintmentDate='" + appintmentDate + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                ", confirmByPatient=" + confirmByPatient +
                ", doctor=" + doctor +
                ", location=" + location +
                '}';
    }

   /* public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(patientId);
        dest.writeValue(doctorId);
        dest.writeValue(locationId);
        dest.writeValue(appintmentDate);
        dest.writeValue(appointmentStatus);
        dest.writeValue(confirmByPatient);
        dest.writeValue(doctor);
        dest.writeValue(location);
    }

    public int describeContents() {
        return  0;
    }*/

}
