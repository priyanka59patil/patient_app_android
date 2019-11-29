package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Encounter implements Serializable {

    @SerializedName("EncountersDataId")
    private Integer encountersDataId;

    @SerializedName("Diagnosis")
    private String diagnosis;

    @SerializedName("Location")
    private String location;

    @SerializedName("EncounterDate")
    private String encounterDate;

    @SerializedName("Paragraph")
    private String paragraph;

    public Integer getEncountersDataId() {
        return encountersDataId;
    }

    public void setEncountersDataId(Integer encountersDataId) {
        this.encountersDataId = encountersDataId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(String encounterDate) {
        this.encounterDate = encounterDate;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public String toString() {
        return "Encounter{" +
                "encountersDataId=" + encountersDataId +
                ", diagnosis='" + diagnosis + '\'' +
                ", location='" + location + '\'' +
                ", encounterDate='" + encounterDate + '\'' +
                ", paragraph='" + paragraph + '\'' +
                '}';
    }
}
