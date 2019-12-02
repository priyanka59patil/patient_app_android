package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InstructionData implements Serializable {
    @SerializedName("InstructionsId")
    private Integer instructionsId;

    @SerializedName("PatientRoleId")
    private Integer patientRoleId;

    @SerializedName("InstructionsList")
    private List<Instruction> instructionsList = null;

    public Integer getInstructionsId() {
        return instructionsId;
    }

    public void setInstructionsId(Integer instructionsId) {
        this.instructionsId = instructionsId;
    }

    public Integer getPatientRoleId() {
        return patientRoleId;
    }

    public void setPatientRoleId(Integer patientRoleId) {
        this.patientRoleId = patientRoleId;
    }

    public List<Instruction> getInstructionsList() {
        return instructionsList;
    }

    public void setInstructionsList(List<Instruction> instructionsList) {
        this.instructionsList = instructionsList;
    }

    @Override
    public String toString() {
        return "InstructionData{" +
                "instructionsId=" + instructionsId +
                ", patientRoleId=" + patientRoleId +
                ", instructionsList=" + instructionsList +
                '}';
    }
}
