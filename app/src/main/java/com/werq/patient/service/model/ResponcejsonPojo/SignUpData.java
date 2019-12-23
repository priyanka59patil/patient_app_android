package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignUpData implements Serializable {

    @SerializedName("User")
    private User User;

    @SerializedName("RefreshToken")
    private String RefreshToken;

    @SerializedName("RefreshTokenExpiryTime")
    private String RefreshTokenExpiryTime;

    @SerializedName("AuthToken")
    private String AuthToken;

    @SerializedName("AuthExpiryTime")
    private String AuthExpiryTime;

    @SerializedName("TempPassChangedFlag")
    private Integer tempPassChangedFlag;

    public User getUser ()
    {
        return User;
    }

    public void setUser (User User)
    {
        this.User = User;
    }

    public String getRefreshToken ()
    {
        return RefreshToken;
    }

    public void setRefreshToken (String RefreshToken)
    {
        this.RefreshToken = RefreshToken;
    }

    public String getRefreshTokenExpiryTime ()
    {
        return RefreshTokenExpiryTime;
    }

    public void setRefreshTokenExpiryTime (String RefreshTokenExpiryTime)
    {
        this.RefreshTokenExpiryTime = RefreshTokenExpiryTime;
    }

    public String getAuthToken ()
    {
        return AuthToken;
    }

    public void setAuthToken (String AuthToken)
    {
        this.AuthToken = AuthToken;
    }

    public String getAuthExpiryTime ()
    {
        return AuthExpiryTime;
    }

    public void setAuthExpiryTime (String AuthExpiryTime)
    {
        this.AuthExpiryTime = AuthExpiryTime;
    }

    public Integer getTempPassChangedFlag() {
        return tempPassChangedFlag;
    }

    public void setTempPassChangedFlag(Integer tempPassChangedFlag) {
        this.tempPassChangedFlag = tempPassChangedFlag;
    }

    @Override
    public String toString() {
        return "SignUpData{" +
                "User=" + User +
                ", RefreshToken='" + RefreshToken + '\'' +
                ", RefreshTokenExpiryTime='" + RefreshTokenExpiryTime + '\'' +
                ", AuthToken='" + AuthToken + '\'' +
                ", AuthExpiryTime='" + AuthExpiryTime + '\'' +
                ", tempPassChangedFlag=" + tempPassChangedFlag +
                '}';
    }
}
