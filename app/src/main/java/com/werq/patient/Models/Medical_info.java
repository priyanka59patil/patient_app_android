package com.werq.patient.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Medical_info implements Parcelable {
    private Medical_details medical_details;

    private String type;

    private String medical_info_id;

    protected Medical_info(Parcel in) {
        type = in.readString();
        medical_info_id = in.readString();
    }

    public static final Creator<Medical_info> CREATOR = new Creator<Medical_info>() {
        @Override
        public Medical_info createFromParcel(Parcel in) {
            return new Medical_info(in);
        }

        @Override
        public Medical_info[] newArray(int size) {
            return new Medical_info[size];
        }
    };

    public Medical_details getMedical_details ()
    {
        return medical_details;
    }

    public void setMedical_details (Medical_details medical_details)
    {
        this.medical_details = medical_details;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getMedical_info_id ()
    {
        return medical_info_id;
    }

    public void setMedical_info_id (String medical_info_id)
    {
        this.medical_info_id = medical_info_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [medical_details = "+medical_details+", type = "+type+", medical_info_id = "+medical_info_id+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(medical_info_id);
    }
}
