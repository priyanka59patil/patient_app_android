package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coworker implements Serializable {

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

    @SerializedName("NPINumber")
    private String nPINumber;

    @SerializedName("IsDeleted")
    private Boolean isDeleted;

    @SerializedName("CreatedAt")
    private String createdAt;

    @SerializedName("JobTitle")
    private JobTitle jobTitle;

    @SerializedName("Speciality")
    private Speciality speciality;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getnPINumber() {
        return nPINumber;
    }

    public void setnPINumber(String nPINumber) {
        this.nPINumber = nPINumber;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Coworker{" +
                "iD=" + iD +
                ", status='" + status + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", nPINumber='" + nPINumber + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdAt='" + createdAt + '\'' +
                ", jobTitle=" + jobTitle +
                ", speciality=" + speciality +
                '}';
    }
}
