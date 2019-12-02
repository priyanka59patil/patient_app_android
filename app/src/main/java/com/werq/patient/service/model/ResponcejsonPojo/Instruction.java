package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Instruction implements Serializable {

    @SerializedName("InstructionsDataId")
    private Integer instructionsDataId;

    @SerializedName("Instructions")
    private String instructions;

    public Integer getInstructionsDataId() {
        return instructionsDataId;
    }

    public void setInstructionsDataId(Integer instructionsDataId) {
        this.instructionsDataId = instructionsDataId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "instructionsDataId=" + instructionsDataId +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
