package com.werq.patient.service.model.ResponcejsonPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AppointmentResult implements Serializable
{

    @SerializedName("ID")
    private Integer iD;

    @SerializedName("PatientId")
    private Integer patientId;

    @SerializedName("DoctorId")
    private Integer doctorId;

    @SerializedName("LocationId")
    private Integer locationId;

    @SerializedName("AppointmentDate")
    private String appintmentDate;

    @SerializedName("AppointmentStatus")
    private String appointmentStatus;

    @SerializedName("ConfirmByPatient")
    private Boolean confirmByPatient;

    @SerializedName("ReferralReason")
    private String referralReason;

    @SerializedName("Doctor")
    private Doctor doctor;

    @SerializedName("Location")
    private Location location;

    @SerializedName("ReferralAttachment")
    private List<AttachmentResult> referralAttachment = null;


    public AppointmentResult() {
    }

    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
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

    public List<AttachmentResult> getReferralAttachment() {
        return referralAttachment;
    }

    public void setReferralAttachment(List<AttachmentResult> referralAttachment) {
        this.referralAttachment = referralAttachment;
    }

    public String getReferralReason() {
        return referralReason;
    }

    public void setReferralReason(String referralReason) {
        this.referralReason = referralReason;
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
                ", referralReason='" + referralReason + '\'' +
                ", doctor=" + doctor +
                ", location=" + location +
                ", referralAttachment=" + referralAttachment +
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
