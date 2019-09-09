package com.werq.patient.Models.pojo;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class AppointmentData implements Parcelable {
    private Provider provider;

    private String referral_id;

    private String appointment_date;

    private String created_at;

    private Files[] files;

    private String schedule_status;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected AppointmentData(Parcel in) {
        referral_id = in.readString();
        appointment_date = in.readString();
        created_at = in.readString();
        schedule_status = in.readString();
        files = in.createTypedArray(Files.CREATOR);
        provider=in.readParcelable(Provider.class.getClassLoader());
    }

    public static final Creator<AppointmentData> CREATOR = new Creator<AppointmentData>() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public AppointmentData createFromParcel(Parcel in) {
            return new AppointmentData(in);
        }

        @Override
        public AppointmentData[] newArray(int size) {
            return new AppointmentData[size];
        }
    };

    public Provider getProvider ()
    {
        return provider;
    }

    public void setProvider (Provider provider)
    {
        this.provider = provider;
    }

    public String getReferral_id ()
    {
        return referral_id;
    }

    public void setReferral_id (String referral_id)
    {
        this.referral_id = referral_id;
    }

    public String getAppointment_date ()
    {
        return appointment_date;
    }

    public void setAppointment_date (String appointment_date)
    {
        this.appointment_date = appointment_date;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public Files[] getFiles ()
    {
        return files;
    }

    public void setFiles (Files[] files)
    {
        this.files = files;
    }

    public String getSchedule_status ()
    {
        return schedule_status;
    }

    public void setSchedule_status (String schedule_status)
    {
        this.schedule_status = schedule_status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [provider = "+provider+", referral_id = "+referral_id+", appointment_date = "+appointment_date+", created_at = "+created_at+", files = "+files+", schedule_status = "+schedule_status+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(referral_id);
        parcel.writeString(appointment_date);
        parcel.writeString(created_at);
        parcel.writeString(schedule_status);
        parcel.writeTypedArray(files,i);
        parcel.writeParcelable(provider,1);
    }
}
