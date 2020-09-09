package com.creative.automc.APIHandler;


import com.creative.automc.APIHandler.Response.AddCarResponse;
import com.creative.automc.APIHandler.Response.BaseResponse;
import com.creative.automc.APIHandler.Response.LoginResponse;
import com.creative.automc.APIHandler.Response.LoginWithCarResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {


    @POST("api/auth/v1")
    @FormUrlEncoded
    Call<LoginWithCarResponse> login(@FieldMap Map<String, String> query);


    @POST("api/auth/v1")
    @Multipart
    Call<LoginResponse> signUp(@Part List<MultipartBody.Part> query);


    @POST("api/cars/v1")
    @FormUrlEncoded
    Call<AddCarResponse> addCar(@FieldMap Map<String, String> query);


    @POST("api/cars/v1")
    @FormUrlEncoded
    Call<BaseResponse> editCar(@FieldMap Map<String, String> query);


}
