package com.werq.patient.service.model.ResponcejsonPojo;

public class SignupResponce {
    private String Message;

    private SignUpData Data;

    private String StatusCode;

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public SignUpData getData ()
    {
        return Data;
    }

    public void setData (SignUpData Data)
    {
        this.Data = Data;
    }

    public String getStatusCode ()
    {
        return StatusCode;
    }

    public void setStatusCode (String StatusCode)
    {
        this.StatusCode = StatusCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Message = "+Message+", Data = "+Data+", StatusCode = "+StatusCode+"]";
    }
}
