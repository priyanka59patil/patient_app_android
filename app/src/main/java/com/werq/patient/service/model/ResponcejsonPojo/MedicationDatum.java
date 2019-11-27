package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MedicationDatum implements Serializable {


    @SerializedName("PatientRoleId")
    private Integer patientRoleId;
    @SerializedName("MedicationId")
    private Integer medicationId;
    @SerializedName("MedicationDataID")
    private Integer medicationDataID;
    @SerializedName("Medication")
    private String medication;
    @SerializedName("GenericCode")
    private String genericCode;
    @SerializedName("RxNorm")
    private String rxNorm;
    @SerializedName("Strength")
    private String strength;
    @SerializedName("StrengthUnit")
    private String strengthUnit;
    @SerializedName("Route")
    private String route;
    @SerializedName("Dose")
    private String dose;
    @SerializedName("DoseForm")
    private String doseForm;
    @SerializedName("Frequency")
    private String frequency;
    @SerializedName("DateStarted")
    private String dateStarted;
    @SerializedName("DateEnded")
    private String dateEnded;
    @SerializedName("Status")
    private String status;
    @SerializedName("Sig")
    private String sig;

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public Integer getMedicationDataID() {
        return medicationDataID;
    }

    public void setMedicationDataID(Integer medicationDataID) {
        this.medicationDataID = medicationDataID;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getGenericCode() {
        return genericCode;
    }

    public void setGenericCode(String genericCode) {
        this.genericCode = genericCode;
    }

    public String getRxNorm() {
        return rxNorm;
    }

    public void setRxNorm(String rxNorm) {
        this.rxNorm = rxNorm;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getStrengthUnit() {
        return strengthUnit;
    }

    public void setStrengthUnit(String strengthUnit) {
        this.strengthUnit = strengthUnit;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDoseForm() {
        return doseForm;
    }

    public void setDoseForm(String doseForm) {
        this.doseForm = doseForm;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(String dateEnded) {
        this.dateEnded = dateEnded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return "MedicationDatum{" +
                "patientRoleId=" + patientRoleId +
                ", medicationId=" + medicationId +
                ", medicationDataID=" + medicationDataID +
                ", medication='" + medication + '\'' +
                ", genericCode='" + genericCode + '\'' +
                ", rxNorm='" + rxNorm + '\'' +
                ", strength='" + strength + '\'' +
                ", strengthUnit='" + strengthUnit + '\'' +
                ", route='" + route + '\'' +
                ", dose='" + dose + '\'' +
                ", doseForm='" + doseForm + '\'' +
                ", frequency='" + frequency + '\'' +
                ", dateStarted='" + dateStarted + '\'' +
                ", dateEnded='" + dateEnded + '\'' +
                ", status='" + status + '\'' +
                ", sig='" + sig + '\'' +
                '}';
    }
}
