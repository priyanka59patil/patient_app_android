package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangePasswordData implements Serializable {

    @SerializedName("User")
    private User user;

    @SerializedName("AuthToken")
    private String authToken;

    @SerializedName("AuthExpiryTime")
    private String authExpiryTime;

    @SerializedName("RefreshToken")
    private String refreshToken;

    @SerializedName("RefreshTokenExpiryTime")
    private String refreshTokenExpiryTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthExpiryTime() {
        return authExpiryTime;
    }

    public void setAuthExpiryTime(String authExpiryTime) {
        this.authExpiryTime = authExpiryTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenExpiryTime() {
        return refreshTokenExpiryTime;
    }

    public void setRefreshTokenExpiryTime(String refreshTokenExpiryTime) {
        this.refreshTokenExpiryTime = refreshTokenExpiryTime;
    }

    @Override
    public String toString() {
        return "ChangePasswordData{" +
                "user=" + user +
                ", authToken='" + authToken + '\'' +
                ", authExpiryTime='" + authExpiryTime + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiryTime='" + refreshTokenExpiryTime + '\'' +
                '}';
    }
}
