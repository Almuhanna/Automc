package com.creative.automc.APIHandler.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {


    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("status")
    private String status;


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


}
