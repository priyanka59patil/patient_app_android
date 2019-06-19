package com.werq.patient.Models;

public class Personal_info {
    private Address address;

    private String last_name;

    private String phone_number;

    private String middle_name;

    private String first_name;

    private String profile_url;

    private String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public Address getAddress ()
    {
        return address;
    }

    public void setAddress (Address address)
    {
        this.address = address;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getPhone_number ()
    {
        return phone_number;
    }

    public void setPhone_number (String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getMiddle_name ()
    {
        return middle_name;
    }

    public void setMiddle_name (String middle_name)
    {
        this.middle_name = middle_name;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", last_name = "+last_name+", phone_number = "+phone_number+", middle_name = "+middle_name+", first_name = "+first_name+"]";
    }
}
