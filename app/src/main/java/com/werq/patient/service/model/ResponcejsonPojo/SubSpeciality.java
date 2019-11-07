
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubSpeciality implements Serializable, Parcelable
{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Code")
    @Expose
    private String code;
    public final static Creator<SubSpeciality> CREATOR = new Creator<SubSpeciality>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SubSpeciality createFromParcel(Parcel in) {
            return new SubSpeciality(in);
        }

        public SubSpeciality[] newArray(int size) {
            return (new SubSpeciality[size]);
        }

    }
    ;

    protected SubSpeciality(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SubSpeciality() {
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

    @Override
    public String toString() {
        return "SubSpeciality{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(code);
    }

    public int describeContents() {
        return  0;
    }

}
