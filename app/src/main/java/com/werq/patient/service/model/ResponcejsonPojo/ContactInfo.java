package com.werq.patient.service.model.ResponcejsonPojo;

public class ContactInfo {
    private String Type;

    private String IsPreferred;

    private String Details;

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getIsPreferred ()
    {
        return IsPreferred;
    }

    public void setIsPreferred (String IsPreferred)
    {
        this.IsPreferred = IsPreferred;
    }

    public String getDetails ()
    {
        return Details;
    }

    public void setDetails (String Details)
    {
        this.Details = Details;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Type = "+Type+", IsPreferred = "+IsPreferred+", Details = "+Details+"]";
    }
}
