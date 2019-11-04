package com.werq.patient.service.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Responce implements Parcelable {
    private ProfileData response;

    protected Responce(Parcel in) {
        response = in.readParcelable(ProfileData.class.getClassLoader());
    }

    public static final Creator<Responce> CREATOR = new Creator<Responce>() {
        @Override
        public Responce createFromParcel(Parcel in) {
            return new Responce(in);
        }

        @Override
        public Responce[] newArray(int size) {
            return new Responce[size];
        }
    };

    public ProfileData getResponse ()
    {
        return response;
    }

    public void setResponse (ProfileData response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(response, i);
    }
}
