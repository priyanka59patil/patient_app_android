package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PatientProfileData implements Serializable {

    @SerializedName("Patient")
    private Patient patient;

    @SerializedName("Insurance")
    private List<Insurance> insurance = null;

/*    @SerializedName("MedicationData")
    private List<MedicationDatum> medicationData = null;*/


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(List<Insurance> insurance) {
        this.insurance = insurance;
    }

    /*public List<MedicationDatum> getMedicationData() {
        return medicationData;
    }

    public void setMedicationData(List<MedicationDatum> medicationData) {
        this.medicationData = medicationData;
    }*/

    @Override
    public String toString() {
        return "PatientProfileData{" +
                "patient=" + patient +
                ", insurance=" + insurance +
              //  ", medicationData=" + medicationData +
                '}';
    }
}
