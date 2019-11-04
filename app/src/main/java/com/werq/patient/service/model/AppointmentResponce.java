package com.werq.patient.service.model;

public class AppointmentResponce {
    private AppointmentData[] response;

    public AppointmentData[] getResponse ()
    {
        return response;
    }

    public void setResponse (AppointmentData[] response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+"]";
    }
}
