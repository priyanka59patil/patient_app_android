package com.werq.patient.service.model;

public class FilesData {
    private Files[] response;

    public Files[] getResponse() {
        return response;
    }

    public void setResponse(Files[] response) {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responce = "+response+"]";
    }
}
