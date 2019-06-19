package com.werq.patient.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileData implements Parcelable {


    private Personal_info personal_info;


    private Insurance_info insurance_info;

    private Medical_info[] medical_info;

    private Medications[] medications;

    public Medications[] getMedications() {
        return medications;
    }

    public void setMedications(Medications[] medications) {
        this.medications = medications;
    }

    public static Creator<ProfileData> getCREATOR() {
        return CREATOR;
    }

    protected ProfileData(Parcel in) {
    }

    public static final Creator<ProfileData> CREATOR = new Creator<ProfileData>() {
        @Override
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        @Override
        public ProfileData[] newArray(int size) {
            return new ProfileData[size];
        }
    };

    public Personal_info getPersonal_info() {
        return personal_info;
    }

    public void setPersonal_info(Personal_info personal_info) {
        this.personal_info = personal_info;
    }

    public Insurance_info getInsurance_info ()
    {
        return insurance_info;
    }

    public void setInsurance_info (Insurance_info insurance_info)
    {
        this.insurance_info = insurance_info;
    }

    public Medical_info[] getMedical_info ()
    {
        return medical_info;
    }

    public void setMedical_info (Medical_info[] medical_info)
    {
        this.medical_info = medical_info;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
