package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor implements Serializable
{

    @SerializedName("ID")
    private String iD;
    @SerializedName("Status")
    private String status;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("NPINumber")
    private String nPINumber;
    @SerializedName("IsDeleted")
    private Boolean isDeleted;
    @SerializedName("ContactInfo")
    private List<ContactInfo> contactInfo = null;
    @SerializedName("Speciality")
    private Speciality speciality;

    public Doctor() {
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getnPINumber() {
        return nPINumber;
    }

    public void setnPINumber(String nPINumber) {
        this.nPINumber = nPINumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "iD=" + iD +
                ", status='" + status + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nPINumber=" + nPINumber +
                ", isDeleted=" + isDeleted +
                ", contactInfo=" + contactInfo +
                ", speciality=" + speciality +
                '}';
    }


}