package com.werq.patient.service.model.RequestJsonPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCredential {

    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Password")
    @Expose
    private String password;

    public final static Parcelable.Creator<UserCredential> CREATOR = new Parcelable.Creator<UserCredential>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserCredential createFromParcel(Parcel in) {
            return new UserCredential(in);
        }

        public UserCredential[] newArray(int size) {
            return (new UserCredential[size]);
        }

    };

    protected UserCredential(Parcel in) {
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserCredential() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(password);
    }

    public int describeContents() {
        return 0;
    }

}
