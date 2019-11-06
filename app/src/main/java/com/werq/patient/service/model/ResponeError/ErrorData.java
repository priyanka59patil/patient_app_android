package com.werq.patient.service.model.ResponeError;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorData implements Serializable, Parcelable {

    @SerializedName("Error")
    @Expose
    private Error error;
    public final static Parcelable.Creator<ErrorData> CREATOR = new Creator<ErrorData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ErrorData createFromParcel(Parcel in) {
            return new ErrorData(in);
        }

        public ErrorData[] newArray(int size) {
            return (new ErrorData[size]);
        }

    };

    protected ErrorData(Parcel in) {
        this.error = ((Error) in.readValue((Error.class.getClassLoader())));
    }

    public ErrorData() {
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "error=" + error +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}
