package com.werq.patient.service.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Provider implements Parcelable {
    private Office office;

    private String speciality;

    private String profile_photo;

    private String last_name;

    private String middle_name;

    private String first_name;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected Provider(Parcel in) {
        speciality = in.readString();
        profile_photo = in.readString();
        last_name = in.readString();
        middle_name = in.readString();
        first_name = in.readString();
        office=in.readParcelable(Office.class.getClassLoader());

    }

    public static final Creator<Provider> CREATOR = new Creator<Provider>() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        @Override
        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };

    public Office getOffice ()
    {
        return office;
    }

    public void setOffice (Office Office)
    {
        this.office = Office;
    }

    public String getSpeciality ()
    {
        return speciality;
    }

    public void setSpeciality (String speciality)
    {
        this.speciality = speciality;
    }

    public String getProfile_photo ()
    {
        return profile_photo;
    }

    public void setProfile_photo (String profile_photo)
    {
        this.profile_photo = profile_photo;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getMiddle_name ()
    {
        return middle_name;
    }

    public void setMiddle_name (String middle_name)
    {
        this.middle_name = middle_name;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Office = "+office+", speciality = "+speciality+", profile_photo = "+profile_photo+", last_name = "+last_name+", middle_name = "+middle_name+", first_name = "+first_name+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(speciality);
        parcel.writeString(profile_photo);
        parcel.writeString(last_name);
        parcel.writeString(middle_name);
        parcel.writeString(first_name);
        parcel.writeParcelable(office,i);

    }
}
