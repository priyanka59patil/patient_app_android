package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("FirstName")
    private String FirstName;

    @SerializedName("DOB")
    private String DOB;

    @SerializedName("Createdat")
    private String Createdat;

    @SerializedName("IsHispanic")
    private String IsHispanic;

    @SerializedName("ContactInfo")
    private ContactInfo[] ContactInfo;

    @SerializedName("ID")
    private String ID;

    @SerializedName("LastName")
    private String LastName;

    @SerializedName("Updatedat")
    private String Updatedat;

    @SerializedName("IsDeceased")
    private String IsDeceased;

    public String getFirstName ()
    {
        return FirstName;
    }

    public void setFirstName (String FirstName)
    {
        this.FirstName = FirstName;
    }

    public String getUserName(){
        return FirstName+" "+LastName;
    }

    public String getDOB ()
    {
        return DOB;
    }

    public void setDOB (String DOB)
    {
        this.DOB = DOB;
    }

    public String getCreatedat ()
    {
        return Createdat;
    }

    public void setCreatedat (String Createdat)
    {
        this.Createdat = Createdat;
    }

    public String getIsHispanic ()
    {
        return IsHispanic;
    }

    public void setIsHispanic (String IsHispanic)
    {
        this.IsHispanic = IsHispanic;
    }

    public ContactInfo[] getContactInfo ()
    {
        return ContactInfo;
    }

    public void setContactInfo (ContactInfo[] ContactInfo)
    {
        this.ContactInfo = ContactInfo;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getLastName ()
    {
        return LastName;
    }

    public void setLastName (String LastName)
    {
        this.LastName = LastName;
    }

    public String getUpdatedat ()
    {
        return Updatedat;
    }

    public void setUpdatedat (String Updatedat)
    {
        this.Updatedat = Updatedat;
    }

    public String getIsDeceased ()
    {
        return IsDeceased;
    }

    public void setIsDeceased (String IsDeceased)
    {
        this.IsDeceased = IsDeceased;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FirstName = "+FirstName+", DOB = "+DOB+", Createdat = "+Createdat+", IsHispanic = "+IsHispanic+", ContactInfo = "+ContactInfo+", ID = "+ID+", LastName = "+LastName+", Updatedat = "+Updatedat+", IsDeceased = "+IsDeceased+"]";
    }
}
