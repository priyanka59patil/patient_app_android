package com.werq.patient.service.model.ResponcejsonPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContactInfo implements Serializable, Parcelable
{

    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("Details")
    @Expose
    private String details;
    @SerializedName("IsPreferred")
    @Expose
    private Boolean isPreferred;
    @SerializedName("Location")
    @Expose
    private String location;
    public final static Parcelable.Creator<ContactInfo> CREATOR = new Creator<ContactInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ContactInfo createFromParcel(Parcel in) {
            return new ContactInfo(in);
        }

        public ContactInfo[] newArray(int size) {
            return (new ContactInfo[size]);
        }

    }
            ;

    protected ContactInfo(Parcel in) {
        this.type = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.details = ((String) in.readValue((String.class.getClassLoader())));
        this.isPreferred = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ContactInfo() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getIsPreferred() {
        return isPreferred;
    }

    public void setIsPreferred(Boolean isPreferred) {
        this.isPreferred = isPreferred;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "type=" + type +
                ", details='" + details + '\'' +
                ", isPreferred=" + isPreferred +
                ", location='" + location + '\'' +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(details);
        dest.writeValue(isPreferred);
        dest.writeValue(location);
    }

    public int describeContents() {
        return  0;
    }

}
