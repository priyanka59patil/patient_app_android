package com.werq.patient.service.model.RequestJsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangePassword implements Serializable {

    @SerializedName("CurrentPassword")
    private String currentPassword;

    @SerializedName("NewPassword")
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ChangePassword{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
