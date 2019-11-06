package com.werq.patient.service.model.RequestJsonPojo;

public class SignUpJson {
    private String Username;

    private String DOB;

    private String InvitationCode;

    private String Password;

    public String getUsername ()
    {
        return Username;
    }

    public void setUsername (String Username)
    {
        this.Username = Username;
    }

    public String getDOB ()
    {
        return DOB;
    }

    public void setDOB (String DOB)
    {
        this.DOB = DOB;
    }

    public String getInvitationCode ()
    {
        return InvitationCode;
    }

    public void setInvitationCode (String InvitationCode)
    {
        this.InvitationCode = InvitationCode;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String Password)
    {
        this.Password = Password;
    }

    @Override
    public String toString()
    {
        return "SignUpJson [Username = "+Username+", DOB = "+DOB+", InvitationCode = "+InvitationCode+", Password = "+Password+"]";
    }
}
