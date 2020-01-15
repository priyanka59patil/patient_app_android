package com.werq.patient.service.model.ResponcejsonPojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatHistoryData implements Serializable {

    @SerializedName("Count")
    private Integer count;

    @SerializedName("Result")
    private ChatHistoryResult result;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ChatHistoryResult getResult() {
        return result;
    }

    public void setResult(ChatHistoryResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ChatHistoryData{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }
}
