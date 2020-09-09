/**
 * Flym
 * <p/>
 * Copyright (c) 2012-2015 Frederic Julian
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * <p/>
 * Some parts of this software are based on "Sparse rss" under the MIT license (see
 * below). Please refers to the original project to identify which parts are under the
 * MIT license.
 * <p/>
 * Copyright (c) 2010-2012 Stefan Handschuh
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.creative.automc.parser;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.creative.automc.APIHandler.ApiClient;
import com.creative.automc.APIHandler.ApiInterface;
import com.creative.automc.APIHandler.DatabaseInterface;
import com.creative.automc.APIHandler.DatabaseInterfacee;
import com.creative.automc.APIHandler.Response.LoginResponse;
import com.creative.automc.APIHandler.Response.LoginWithCarResponse;
import com.creative.automc.activity.MainActivity;
import com.creative.automc.activity.AddCarActivity;
import com.creative.automc.utils.PrefManager;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoreParser extends DefaultHandler {


    public static boolean login(final Context context, String Email, String Password, final @Nullable DatabaseInterfacee callbacks) {

        Map<String, String> queryـmap = new HashMap<String, String>();

        queryـmap.put("query[action]", "userLogin");
        queryـmap.put("query[input]", Email);
        queryـmap.put("query[password]", Password);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginWithCarResponse> call = apiService.login(queryـmap);
        call.enqueue(new Callback<LoginWithCarResponse>() {
            @Override
            public void onResponse(Call<LoginWithCarResponse> call, Response<LoginWithCarResponse> response) {

                LoginWithCarResponse sourcesResponse = response.body();

                if (response.isSuccessful()) {
                    try {

                        if (response.code() == 200) {
                            if (sourcesResponse != null) {
                                if (sourcesResponse.getStatus().equals("success")) {
                                    if (sourcesResponse.getData() != null) {

                                        PrefManager.setUserLoggedIn(context, true);
                                        PrefManager.setUserId(context, sourcesResponse.getData().getUser().getId());
                                        PrefManager.setUserFullName(context, sourcesResponse.getData().getUser().getFull_name());
                                        PrefManager.setUserEmail(context, sourcesResponse.getData().getUser().getEmail());
                                        PrefManager.setUserLocation(context, sourcesResponse.getData().getUser().getLocation());
                                        PrefManager.setUserToken(context, sourcesResponse.getData().getUser().getToken());


                                        PrefManager.setUserLoggedIn(context, true);
                                        PrefManager.setCarVinNumber(context, sourcesResponse.getData().getCar().getVin_number());
                                        PrefManager.setCarBrand(context, sourcesResponse.getData().getCar().getBrand());
                                        PrefManager.setCarModel(context, sourcesResponse.getData().getCar().getModel());
                                        PrefManager.setCarYear(context, String.valueOf(sourcesResponse.getData().getCar().getYear()));
                                        PrefManager.setCarDistance(context, String.valueOf(sourcesResponse.getData().getCar().getCar_distance()));
                                        PrefManager.setCarLastMDistance(context, sourcesResponse.getData().getCar().getLast_m_distance());
                                        PrefManager.setCarLastMDate(context, sourcesResponse.getData().getCar().getLast_m_date());


                                        if (PrefManager.getCarVinNumber(context).equals("")) {
                                            Intent myIntent = new Intent(context, AddCarActivity.class);
                                            context.startActivity(myIntent);
                                        } else {
                                            Intent myIntent = new Intent(context, MainActivity.class);
                                            context.startActivity(myIntent);
                                        }

                                        if (callbacks != null)
                                            callbacks.onSuccess(true);

                                    }
                                } else {

                                    if (callbacks != null)
                                        callbacks.onFailed(sourcesResponse.getMessagesBag());

                                }


                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (callbacks != null)
                            callbacks.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginWithCarResponse> call, Throwable t) {
                t.printStackTrace();
                if (callbacks != null)
                    callbacks.onError(t);
            }


        });


        return true;

    }

    public static boolean signup(final Context context, String FisrtName, String LastName, String Email,
                                 String Location, String Password, final @Nullable DatabaseInterface callbacks) {


        List<MultipartBody.Part> userSignUp = new ArrayList<>();
        userSignUp.add(MultipartBody.Part.createFormData("query[action]", "userSignUp"));
        userSignUp.add(MultipartBody.Part.createFormData("query[first_name]", FisrtName));
        userSignUp.add(MultipartBody.Part.createFormData("query[last_name]", LastName));
        userSignUp.add(MultipartBody.Part.createFormData("query[email]", Email));
        userSignUp.add(MultipartBody.Part.createFormData("query[location]", Location));
        userSignUp.add(MultipartBody.Part.createFormData("query[password]", Password));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.signUp(userSignUp);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse sourcesResponse = response.body();

                if (response.isSuccessful()) {
                    try {

                        if (response.code() == 200) {
                            if (sourcesResponse != null) {
                                if (sourcesResponse.getStatus().equals("success")) {
                                    if (sourcesResponse.getData() != null) {

                                        PrefManager.setUserLoggedIn(context, true);
                                        PrefManager.setUserId(context, sourcesResponse.getData().getId());
                                        PrefManager.setUserFullName(context, sourcesResponse.getData().getFull_name());
                                        PrefManager.setUserEmail(context, sourcesResponse.getData().getEmail());
                                        PrefManager.setUserToken(context, sourcesResponse.getData().getToken());

                                        if (callbacks != null)
                                            callbacks.onSuccess(true);

                                    }
                                } else {

                                    // if (callbacks != null)
                                    // callbacks.onFailed(sourcesResponse.getMessagesBag());

                                    if (sourcesResponse.getMessagesBag().getEmail() != null)
                                        Toast.makeText(context, "" + sourcesResponse.getMessagesBag().getEmail(), Toast.LENGTH_SHORT).show();

                                    if (sourcesResponse.getMessagesBag().getPassword() != null)
                                        Toast.makeText(context, "" + sourcesResponse.getMessagesBag().getPassword(), Toast.LENGTH_SHORT).show();


                                }

                                // }
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (callbacks != null)
                            callbacks.onError(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();

                if (callbacks != null)
                    callbacks.onError(t);
            }


        });


        return true;

    }


}
