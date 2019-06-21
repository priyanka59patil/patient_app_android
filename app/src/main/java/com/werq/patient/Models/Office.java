package com.werq.patient.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Office implements Parcelable {
    private String country;

    private String about_Office;

    private String address2;

    private String city;

    private String address1;

    private String latitude;

    private String organization_name;

    private String org_npi;

    private String phone_number;

    private String id;

    private String state;

    private String postal_code;

    private String org_location_id;

    private String longitude;

    protected Office(Parcel in) {
        country = in.readString();
        about_Office = in.readString();
        address2 = in.readString();
        city = in.readString();
        address1 = in.readString();
        latitude = in.readString();
        organization_name = in.readString();
        org_npi = in.readString();
        phone_number = in.readString();
        id = in.readString();
        state = in.readString();
        postal_code = in.readString();
        org_location_id = in.readString();
        longitude = in.readString();
    }

    public static final Creator<Office> CREATOR = new Creator<Office>() {
        @Override
        public Office createFromParcel(Parcel in) {
            return new Office(in);
        }

        @Override
        public Office[] newArray(int size) {
            return new Office[size];
        }
    };

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getAbout_Office ()
    {
        return about_Office;
    }

    public void setAbout_Office (String about_Office)
    {
        this.about_Office = about_Office;
    }

    public String getAddress2 ()
    {
        return address2;
    }

    public void setAddress2 (String address2)
    {
        this.address2 = address2;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getAddress1 ()
    {
        return address1;
    }

    public void setAddress1 (String address1)
    {
        this.address1 = address1;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getOrganization_name ()
    {
        return organization_name;
    }

    public void setOrganization_name (String organization_name)
    {
        this.organization_name = organization_name;
    }

    public String getOrg_npi ()
    {
        return org_npi;
    }

    public void setOrg_npi (String org_npi)
    {
        this.org_npi = org_npi;
    }

    public String getPhone_number ()
    {
        return phone_number;
    }

    public void setPhone_number (String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getPostal_code ()
    {
        return postal_code;
    }

    public void setPostal_code (String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getOrg_location_id ()
    {
        return org_location_id;
    }

    public void setOrg_location_id (String org_location_id)
    {
        this.org_location_id = org_location_id;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return ""+address1+","+address2+" , "+city+","+state+","+postal_code+"";

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeString(about_Office);
        parcel.writeString(address2);
        parcel.writeString(city);
        parcel.writeString(address1);
        parcel.writeString(latitude);
        parcel.writeString(organization_name);
        parcel.writeString(org_npi);
        parcel.writeString(phone_number);
        parcel.writeString(id);
        parcel.writeString(state);
        parcel.writeString(postal_code);
        parcel.writeString(org_location_id);
        parcel.writeString(longitude);
    }
}
