package com.creative.automc.APIHandler.Response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginWithCarResponse {


    @Expose
    @SerializedName("data")
    private Data data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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

    public static class Data {
        @Expose
        @SerializedName("car")
        private Car car;
        @Expose
        @SerializedName("user")
        private User user;

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public static class Car {
        @Expose
        @SerializedName("updated_at")
        private String updated_at;
        @Expose
        @SerializedName("created_at")
        private String created_at;
        @Expose
        @SerializedName("last_m_date")
        private String last_m_date;
        @Expose
        @SerializedName("last_m_distance")
        private String last_m_distance;
        @Expose
        @SerializedName("car_distance")
        private int car_distance;
        @Expose
        @SerializedName("year")
        private int year;
        @Expose
        @SerializedName("model")
        private String model;
        @Expose
        @SerializedName("brand")
        private String brand;
        @Expose
        @SerializedName("vin_number")
        private String vin_number;
        @Expose
        @SerializedName("user_id")
        private int user_id;
        @Expose
        @SerializedName("id")
        private int id;

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getLast_m_date() {
            return last_m_date;
        }

        public void setLast_m_date(String last_m_date) {
            this.last_m_date = last_m_date;
        }

        public String getLast_m_distance() {
            return last_m_distance;
        }

        public void setLast_m_distance(String last_m_distance) {
            this.last_m_distance = last_m_distance;
        }

        public int getCar_distance() {
            return car_distance;
        }

        public void setCar_distance(int car_distance) {
            this.car_distance = car_distance;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getVin_number() {
            return vin_number;
        }

        public void setVin_number(String vin_number) {
            this.vin_number = vin_number;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class User {
        @Expose
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("location")
        private String location;
        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("full_name")
        private String full_name;
        @Expose
        @SerializedName("id")
        private int id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
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
    }

    public static class MessagesBag {
        @Expose
        @SerializedName("success_process")
        private List<String> success_process;

        public List<String> getSuccess_process() {
            return success_process;
        }

        public void setSuccess_process(List<String> success_process) {
            this.success_process = success_process;
        }
    }
}
