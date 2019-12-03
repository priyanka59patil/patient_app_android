
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryOfProcedure implements Serializable
{

    @SerializedName("HistoryOfProcedureDataId")
    private Integer historyOfProcedureDataId;
    @SerializedName("Code")
    private String code;
    @SerializedName("Site")
    private String site;
    @SerializedName("DateDiagnosed")
    private String dateDiagnosed;
    @SerializedName("Status")
    private String status;

    public Integer getHistoryOfProcedureDataId() {
        return historyOfProcedureDataId;
    }

    public void setHistoryOfProcedureDataId(Integer historyOfProcedureDataId) {
        this.historyOfProcedureDataId = historyOfProcedureDataId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDateDiagnosed() {
        return dateDiagnosed;
    }

    public void setDateDiagnosed(String dateDiagnosed) {
        this.dateDiagnosed = dateDiagnosed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HistoryOfProcedure{" +
                "historyOfProcedureDataId=" + historyOfProcedureDataId +
                ", code='" + code + '\'' +
                ", site='" + site + '\'' +
                ", dateDiagnosed='" + dateDiagnosed + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
