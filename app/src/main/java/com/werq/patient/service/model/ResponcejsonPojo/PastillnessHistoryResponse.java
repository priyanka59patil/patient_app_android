
package com.werq.patient.service.model.ResponcejsonPojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PastillnessHistoryResponse extends BaseResponse implements Serializable
{

    @SerializedName("Data")
    private PastillnessData data;

    public PastillnessData getData() {
        return data;
    }

    public void setData(PastillnessData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PastillnessHistoryResponse{" +super.toString()+
                "data=" + data +
                '}';
    }
}
