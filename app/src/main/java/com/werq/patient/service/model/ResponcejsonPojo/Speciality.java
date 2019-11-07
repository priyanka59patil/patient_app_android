
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speciality implements Serializable, Parcelable
{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("SubSpeciality")
    @Expose
    private List<SubSpeciality> subSpeciality = null;
    public final static Creator<Speciality> CREATOR = new Creator<Speciality>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Speciality createFromParcel(Parcel in) {
            return new Speciality(in);
        }

        public Speciality[] newArray(int size) {
            return (new Speciality[size]);
        }

    }
    ;

    protected Speciality(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.subSpeciality, (SubSpeciality.class.getClassLoader()));
    }

    public Speciality() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SubSpeciality> getSubSpeciality() {
        return subSpeciality;
    }

    public void setSubSpeciality(List<SubSpeciality> subSpeciality) {
        this.subSpeciality = subSpeciality;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", subSpeciality=" + subSpeciality +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(code);
        dest.writeList(subSpeciality);
    }

    public int describeContents() {
        return  0;
    }

}
