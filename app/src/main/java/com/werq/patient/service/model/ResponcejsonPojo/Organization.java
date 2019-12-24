package com.werq.patient.service.model.ResponcejsonPojo;

public class Organization {

    String locationId;
    String locationAddress;

    public Organization(String locationId, String locationAddress) {
        this.locationId = locationId;
        this.locationAddress = locationAddress;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }


    @Override
    public String toString() {
        return "Organization{" +
                "locationId='" + locationId + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                '}';
    }
}
