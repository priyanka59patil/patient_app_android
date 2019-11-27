package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable {

    @SerializedName("ID")
    private Integer ID;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("MiddleName")
    private String middleName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("DOB")
    private String DOB;
    @SerializedName("Sex")
    private String sex;
    @SerializedName("ProfilePhoto")
    private String profilePhoto;
    @SerializedName("Race")
    private String race;
    @SerializedName("MaritalStatus")
    private String maritalStatus;
    @SerializedName("IsHispanic")
    private Boolean isHispanic;
    @SerializedName("IsDeceased")
    private Boolean isDeceased;
    @SerializedName("Address1")
    private String address1;
    @SerializedName("Address2")
    private String address2;
    @SerializedName("City")
    private String city;
    @SerializedName("State")
    private String state;
    @SerializedName("PostalCode")
    private Integer postalCode;
    @SerializedName("Country")
    private String country;
    @SerializedName("Createdat")
    private String createdate;
    @SerializedName("Updatedat")
    private String updatedate;
    @SerializedName("ContactInfo")
    private List<ContactInfo> contactInfo = null;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
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


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Boolean getIsHispanic() {
        return isHispanic;
    }

    public void setIsHispanic(Boolean isHispanic) {
        this.isHispanic = isHispanic;
    }

    public Boolean getIsDeceased() {
        return isDeceased;
    }

    public void setIsDeceased(Boolean isDeceased) {
        this.isDeceased = isDeceased;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public List<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", sex='" + sex + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", race='" + race + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", isHispanic=" + isHispanic +
                ", isDeceased=" + isDeceased +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                ", createdate='" + createdate + '\'' +
                ", updatedate='" + updatedate + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
