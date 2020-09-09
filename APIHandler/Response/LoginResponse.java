package com.creative.automc.APIHandler.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse extends BaseResponse {


    @Expose
    @SerializedName("messagesBag")
    private MessagesBag messagesBag;
    @Expose
    @SerializedName("data")
    private Data data;

    public MessagesBag getMessagesBag() {
        return messagesBag;
    }

    public void setMessagesBag(MessagesBag messagesBag) {
        this.messagesBag = messagesBag;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class MessagesBag {
        @Expose
        @SerializedName("success_process")
        private List<String> success_process;
        @Expose
        @SerializedName("proccess_failed")
        private List<String> proccess_failed;

        @Expose
        @SerializedName("email")
        private List<String> email;
        @Expose
        @SerializedName("password")
        private List<String> password;

        public List<String> getSuccess_process() {
            return success_process;
        }

        public void setSuccess_process(List<String> success_process) {
            this.success_process = success_process;
        }

        public List<String> getProccess_failed() {
            return proccess_failed;
        }

        public void setProccess_failed(List<String> proccess_failed) {
            this.proccess_failed = proccess_failed;
        }


        public List<String> getEmail() {
            return email;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }


    }


    public static class Data {
        @Expose
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("full_name")
        private String full_name;
        @Expose
        @SerializedName("location")
        private String location;
        @Expose
        @SerializedName("id")
        private int id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }


}
