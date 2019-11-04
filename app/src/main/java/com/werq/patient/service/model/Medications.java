package com.werq.patient.service.model;

public class Medications {
    private String dosage;

    private String effectivedates;

    private String instruction;

    private String medication;

    public String getDosage ()
    {
        return dosage;
    }

    public void setDosage (String dosage)
    {
        this.dosage = dosage;
    }

    public String getEffectivedates ()
    {
        return effectivedates;
    }

    public void setEffectivedates (String effectivedates)
    {
        this.effectivedates = effectivedates;
    }

    public String getInstruction ()
    {
        return instruction;
    }

    public void setInstruction (String instruction)
    {
        this.instruction = instruction;
    }

    public String getMedication ()
    {
        return medication;
    }

    public void setMedication (String medication)
    {
        this.medication = medication;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dosage = "+dosage+", effective dates = "+effectivedates+", instruction = "+instruction+", medication = "+medication+"]";
    }
}
