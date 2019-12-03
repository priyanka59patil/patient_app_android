
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allergy implements Serializable
{

    @SerializedName("AllergyDataId")
    private Integer allergyDataId;

    @SerializedName("Substance")
    private String substance;

    @SerializedName("RxNorm")
    private String rxNorm;

    @SerializedName("Reactions")
    private String reactions;

    @SerializedName("Severity")
    private String severity;

    @SerializedName("Status")
    private String status;

    @SerializedName("StartDate")
    private String startDate;

    public Integer getAllergyDataId() {
        return allergyDataId;
    }

    public void setAllergyDataId(Integer allergyDataId) {
        this.allergyDataId = allergyDataId;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getRxNorm() {
        return rxNorm;
    }

    public void setRxNorm(String rxNorm) {
        this.rxNorm = rxNorm;
    }

    public String getReactions() {
        return reactions;
    }

    public void setReactions(String reactions) {
        this.reactions = reactions;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "allergyDataId=" + allergyDataId +
                ", substance='" + substance + '\'' +
                ", rxNorm='" + rxNorm + '\'' +
                ", reactions='" + reactions + '\'' +
                ", severity='" + severity + '\'' +
                ", status='" + status + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
