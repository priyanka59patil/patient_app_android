package com.werq.patient.Models;

public class Medical_details {
    private String title;

    private String value;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", value = "+value+"]";
    }
}