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
    private Integer iD;

    @SerializedName("Status")
    private String status;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("MiddleName")
    private String middleName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("ProfilePhoto")
    private String profilePhoto;

    @SerializedName("AboutMe")
    @Expose
    private String aboutMe;

    @SerializedName("NPINumber")
    private Integer nPINumber;

    @SerializedName("IsDeleted")
    private Boolean isDeleted;

    @SerializedName("ContactInfo")
    private List<ContactInfo> contactInfo = null;

    @SerializedName("Speciality")
    private Speciality speciality;

    @SerializedName("CreatedAt")
    private String createdAt;

    @SerializedName("JobTitle")
    private JobTitle jobTitle;

    public Doctor() {
    }

    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public Integer getnPINumber() {
        return nPINumber;
    }

    public void setnPINumber(Integer nPINumber) {
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "iD=" + iD +
                ", status='" + status + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", nPINumber='" + nPINumber + '\'' +
                ", isDeleted=" + isDeleted +
                ", contactInfo=" + contactInfo +
                ", speciality=" + speciality +
                ", createdAt='" + createdAt + '\'' +
                ", jobTitle=" + jobTitle +
                '}';
    }
}