
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Serializable
{

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("OrgNpi")
    @Expose
    private String orgNpi;
    @SerializedName("CorpNpi")
    @Expose
    private String corpNpi;

    @SerializedName("Address1")
    @Expose
    private String address1;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Postalcode")
    @Expose
    private String postalcode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CreatedAt")
    @Expose
    private String createdAt;
    @SerializedName("UpdatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("OrganizationName")
    @Expose
    private String organizationName;

    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;

    public Location() {
    }


    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
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

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public String getOrgNpi() {
        return orgNpi;
    }

    public void setOrgNpi(String orgNpi) {
        this.orgNpi = orgNpi;
    }

    public String getCorpNpi() {
        return corpNpi;
    }

    public void setCorpNpi(String corpNpi) {
        this.corpNpi = corpNpi;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Location{" +
                "iD=" + iD +
                ", orgNpi='" + orgNpi + '\'' +
                ", corpNpi='" + corpNpi + '\'' +
                ", address1='" + address1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", country='" + country + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", isDeleted=" + isDeleted +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
