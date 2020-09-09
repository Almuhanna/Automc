package com.creative.automc.APIHandler.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateResponse {


    @Expose
    @SerializedName("messagesBag")
    private MessagesBag messagesBag;
    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("status")
    private String status;

    public MessagesBag getMessagesBag() {
        return messagesBag;
    }

    public void setMessagesBag(MessagesBag messagesBag) {
        this.messagesBag = messagesBag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MessagesBag {
        @Expose
        @SerializedName("proccess")
        private List<String> proccess;

        public List<String> getProccess() {
            return proccess;
        }

        public void setProccess(List<String> proccess) {
            this.proccess = proccess;
        }
    }
}
