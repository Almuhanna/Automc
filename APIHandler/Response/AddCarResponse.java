package com.creative.automc.APIHandler.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddCarResponse {


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
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("created_at")
        private String created_at;
        @Expose
        @SerializedName("updated_at")
        private String updated_at;
        @Expose
        @SerializedName("air_conditioner_life")
        private String air_conditioner_life;
        @Expose
        @SerializedName("breaks_life")
        private String breaks_life;
        @Expose
        @SerializedName("tires_life")
        private String tires_life;
        @Expose
        @SerializedName("oil_life")
        private String oil_life;
        @Expose
        @SerializedName("last_m_date")
        private String last_m_date;
        @Expose
        @SerializedName("last_m_distance")
        private String last_m_distance;
        @Expose
        @SerializedName("car_distance")
        private String car_distance;
        @Expose
        @SerializedName("year")
        private String year;
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
        private String user_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getAir_conditioner_life() {
            return air_conditioner_life;
        }

        public void setAir_conditioner_life(String air_conditioner_life) {
            this.air_conditioner_life = air_conditioner_life;
        }

        public String getBreaks_life() {
            return breaks_life;
        }

        public void setBreaks_life(String breaks_life) {
            this.breaks_life = breaks_life;
        }

        public String getTires_life() {
            return tires_life;
        }

        public void setTires_life(String tires_life) {
            this.tires_life = tires_life;
        }

        public String getOil_life() {
            return oil_life;
        }

        public void setOil_life(String oil_life) {
            this.oil_life = oil_life;
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

        public String getCar_distance() {
            return car_distance;
        }

        public void setCar_distance(String car_distance) {
            this.car_distance = car_distance;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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
