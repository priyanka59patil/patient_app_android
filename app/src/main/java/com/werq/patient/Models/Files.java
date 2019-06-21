package com.werq.patient.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Files implements Parcelable {
    private String file_url;

    private String file_name;

    private String file_type;

    private String created_at;

    private  Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public static Creator<Files> getCREATOR() {
        return CREATOR;
    }

    protected Files(Parcel in) {
        file_url = in.readString();
        file_name = in.readString();
        file_type = in.readString();
        created_at = in.readString();
        provider=in.readParcelable(Provider.class.getClassLoader());
    }

    public static final Creator<Files> CREATOR = new Creator<Files>() {
        @Override
        public Files createFromParcel(Parcel in) {
            return new Files(in);
        }

        @Override
        public Files[] newArray(int size) {
            return new Files[size];
        }
    };

    public String getFile_url ()
    {
        return file_url;
    }

    public void setFile_url (String file_url)
    {
        this.file_url = file_url;
    }

    public String getFile_name ()
    {
        return file_name;
    }

    public void setFile_name (String file_name)
    {
        this.file_name = file_name;
    }

    public String getFile_type ()
    {
        return file_type;
    }

    public void setFile_type (String file_type)
    {
        this.file_type = file_type;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [file_url = "+file_url+", file_name = "+file_name+", file_type = "+file_type+", created_at = "+created_at+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(file_url);
        parcel.writeString(file_name);
        parcel.writeString(file_type);
        parcel.writeString(created_at);
        parcel.writeParcelable(provider,i);
    }
}
