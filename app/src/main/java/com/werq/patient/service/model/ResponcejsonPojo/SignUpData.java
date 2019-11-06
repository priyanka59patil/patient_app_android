package com.werq.patient.service.model.ResponcejsonPojo;

public class SignUpData {
    private User User;

    private String RefreshToken;

    private String RefreshTokenExpiryTime;

    private String AuthToken;

    private String AuthExpiryTime;

    public User getUser ()
    {
        return User;
    }

    public void setUser (User User)
    {
        this.User = User;
    }

    public String getRefreshToken ()
    {
        return RefreshToken;
    }

    public void setRefreshToken (String RefreshToken)
    {
        this.RefreshToken = RefreshToken;
    }

    public String getRefreshTokenExpiryTime ()
    {
        return RefreshTokenExpiryTime;
    }

    public void setRefreshTokenExpiryTime (String RefreshTokenExpiryTime)
    {
        this.RefreshTokenExpiryTime = RefreshTokenExpiryTime;
    }

    public String getAuthToken ()
    {
        return AuthToken;
    }

    public void setAuthToken (String AuthToken)
    {
        this.AuthToken = AuthToken;
    }

    public String getAuthExpiryTime ()
    {
        return AuthExpiryTime;
    }

    public void setAuthExpiryTime (String AuthExpiryTime)
    {
        this.AuthExpiryTime = AuthExpiryTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [User = "+User+", RefreshToken = "+RefreshToken+", RefreshTokenExpiryTime = "+RefreshTokenExpiryTime+", AuthToken = "+AuthToken+", AuthExpiryTime = "+AuthExpiryTime+"]";
    }
}
