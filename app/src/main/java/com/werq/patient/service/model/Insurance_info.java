package com.werq.patient.service.model;

public class Insurance_info {

    private String front_card_url;

    private String member_id;

    private String patient_phone_number;

    private String coverage_dates;

    private String patient_dob;

    private String patient_address;

    private String subscriber_dob;

    private String insurance_address;

    private String back_card_url;

    private String insurance_type;

    private String relationship_subscriber;

    private String subscriber_id;

    private String group_number;

    private String insurance_phone_number;

    public String getFront_card_url ()
    {
        return front_card_url;
    }

    public void setFront_card_url (String front_card_url)
    {
        this.front_card_url = front_card_url;
    }

    public String getMember_id ()
    {
        return member_id;
    }

    public void setMember_id (String member_id)
    {
        this.member_id = member_id;
    }

    public String getPatient_phone_number ()
    {
        return patient_phone_number;
    }

    public void setPatient_phone_number (String patient_phone_number)
    {
        this.patient_phone_number = patient_phone_number;
    }

    public String getCoverage_dates ()
    {
        return coverage_dates;
    }

    public void setCoverage_dates (String coverage_dates)
    {
        this.coverage_dates = coverage_dates;
    }

    public String getPatient_dob ()
    {
        return patient_dob;
    }

    public void setPatient_dob (String patient_dob)
    {
        this.patient_dob = patient_dob;
    }

    public String getPatient_address ()
    {
        return patient_address;
    }

    public void setPatient_address (String patient_address)
    {
        this.patient_address = patient_address;
    }

    public String getSubscriber_dob ()
    {
        return subscriber_dob;
    }

    public void setSubscriber_dob (String subscriber_dob)
    {
        this.subscriber_dob = subscriber_dob;
    }

    public String getInsurance_address ()
    {
        return insurance_address;
    }

    public void setInsurance_address (String insurance_address)
    {
        this.insurance_address = insurance_address;
    }

    public String getBack_card_url ()
    {
        return back_card_url;
    }

    public void setBack_card_url (String back_card_url)
    {
        this.back_card_url = back_card_url;
    }

    public String getInsurance_type ()
    {
        return insurance_type;
    }

    public void setInsurance_type (String insurance_type)
    {
        this.insurance_type = insurance_type;
    }

    public String getRelationship_subscriber ()
    {
        return relationship_subscriber;
    }

    public void setRelationship_subscriber (String relationship_subscriber)
    {
        this.relationship_subscriber = relationship_subscriber;
    }

    public String getSubscriber_id ()
    {
        return subscriber_id;
    }

    public void setSubscriber_id (String subscriber_id)
    {
        this.subscriber_id = subscriber_id;
    }

    public String getGroup_number ()
    {
        return group_number;
    }

    public void setGroup_number (String group_number)
    {
        this.group_number = group_number;
    }

    public String getInsurance_phone_number ()
    {
        return insurance_phone_number;
    }

    public void setInsurance_phone_number (String insurance_phone_number)
    {
        this.insurance_phone_number = insurance_phone_number;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [front_card_url = "+front_card_url+", member_id = "+member_id+", patient_phone_number = "+patient_phone_number+", coverage_dates = "+coverage_dates+", patient_dob = "+patient_dob+", patient_address = "+patient_address+", subscriber_dob = "+subscriber_dob+", insurance_address = "+insurance_address+", back_card_url = "+back_card_url+", insurance_type = "+insurance_type+", relationship_subscriber = "+relationship_subscriber+", subscriber_id = "+subscriber_id+", group_number = "+group_number+", insurance_phone_number = "+insurance_phone_number+"]";
    }
}
