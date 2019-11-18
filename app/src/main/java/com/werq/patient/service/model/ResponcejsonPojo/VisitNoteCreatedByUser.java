
package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VisitNoteCreatedByUser implements Serializable
{

    @SerializedName("ID")
    private Integer iD;

    @SerializedName("Status")
    private String status;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("MiddleName")
    @Expose
    private String middleName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("ProfilePhoto")
    @Expose
    private String profilePhoto;
    @SerializedName("NPINumber")
    @Expose
    private Integer nPINumber;

    @SerializedName("IsDeleted")
    private Boolean isDeleted;

    @SerializedName("JobTitle")
    @Expose
    private JobTitle jobTitle;

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

    public Integer getnPINumber() {
        return nPINumber;
    }

    public void setnPINumber(Integer nPINumber) {
        this.nPINumber = nPINumber;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "VisitNoteCreatedByUser{" +
                "iD=" + iD +
                ", status='" + status + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", nPINumber=" + nPINumber +
                ", isDeleted=" + isDeleted +
                ", jobTitle=" + jobTitle +
                '}';
    }
}
