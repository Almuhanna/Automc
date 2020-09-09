package com.creative.automc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.creative.automc.APIHandler.ApiClient;
import com.creative.automc.APIHandler.ApiInterface;
import com.creative.automc.APIHandler.Response.AddCarResponse;
import com.creative.automc.R;
import com.creative.automc.utils.InputFilterMinMax;
import com.creative.automc.utils.PrefManager;
import com.creative.automc.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class AddCarActivity extends AppCompatActivity {

    AwesomeValidation mAwesomeValidation;
    ProgressBar loadingimage;

    Button sendbtn;


    EditText vin_number_edittext, brand_edittext, modeledittext,
            yearedittext, car_distance_edittext, last_maintenance_distance_edittext, last_maintenance_date_edittext,
            oil_lifeedittext, tires_lifeedittext, breaks_lifeedittext, air_conditioner_lifeedittext;

    TextInputLayout vin_number, brand, model, year, car_distance, last_maintenance_distance, last_maintenance_date,
            oil_life, tires_life, breaks_life, air_conditioner_life;

    RelativeLayout dimlayout, senderrorlayout;


    public static void start(Context context) {
        Intent starter = new Intent(context, AddCarActivity.class);

        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        loadingimage = findViewById(R.id.loadingimage);
        dimlayout = findViewById(R.id.dimlayout);
        senderrorlayout = findViewById(R.id.senderrorlayout);

        vin_number_edittext = findViewById(R.id.vin_number_edittext);
        brand_edittext = findViewById(R.id.brand_edittext);
        modeledittext = findViewById(R.id.modeledittext);
        yearedittext = findViewById(R.id.yearedittext);
        car_distance_edittext = findViewById(R.id.car_distance_edittext);
        last_maintenance_distance_edittext = findViewById(R.id.last_maintenance_distance_edittext);
        last_maintenance_date_edittext = findViewById(R.id.last_maintenance_date_edittext);

        oil_lifeedittext = findViewById(R.id.oil_life_edittext);
        tires_lifeedittext = findViewById(R.id.tires_life_edittext);
        breaks_lifeedittext = findViewById(R.id.breaks_life_edittext);
        air_conditioner_lifeedittext = findViewById(R.id.air_conditioner_life_edittext);

        vin_number = findViewById(R.id.vin_number);
        model = findViewById(R.id.model);
        brand = findViewById(R.id.brand);
        year = findViewById(R.id.year);
        car_distance = findViewById(R.id.car_distance);
        last_maintenance_distance = findViewById(R.id.last_maintenance_distance);
        last_maintenance_date = findViewById(R.id.last_maintenance_date);

        oil_life = findViewById(R.id.oil_life);
        tires_life = findViewById(R.id.tires_life);
        breaks_life = findViewById(R.id.breaks_life);
        air_conditioner_life = findViewById(R.id.air_conditioner_life);


        oil_lifeedittext.setFilters(new InputFilter[]{new InputFilterMinMax("1", "100")});
        tires_lifeedittext.setFilters(new InputFilter[]{new InputFilterMinMax("1", "100")});
        breaks_lifeedittext.setFilters(new InputFilter[]{new InputFilterMinMax("1", "100")});
        air_conditioner_lifeedittext.setFilters(new InputFilter[]{new InputFilterMinMax("1", "100")});


        sendbtn = findViewById(R.id.sendbtn);


        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
        // setUnderlabelColor is optional for UNDERLABEL style, by default it's holo_red_light

        mAwesomeValidation.setUnderlabelColorByResource(android.R.color.holo_red_dark); // optional for UNDERLABEL style

        mAwesomeValidation.addValidation(this, R.id.vin_number, RegexTemplate.NOT_EMPTY, R.string.err_vin);
        mAwesomeValidation.addValidation(this, R.id.model, RegexTemplate.NOT_EMPTY, R.string.err_model);
        mAwesomeValidation.addValidation(this, R.id.brand, RegexTemplate.NOT_EMPTY, R.string.err_brand);
        mAwesomeValidation.addValidation(this, R.id.year, RegexTemplate.NOT_EMPTY, R.string.err_year);
        mAwesomeValidation.addValidation(this, R.id.car_distance, RegexTemplate.NOT_EMPTY, R.string.err_car_distance);
        mAwesomeValidation.addValidation(this, R.id.last_maintenance_distance, RegexTemplate.NOT_EMPTY, R.string.err_last_distance);
        mAwesomeValidation.addValidation(this, R.id.last_maintenance_date, RegexTemplate.NOT_EMPTY, R.string.err_last_date);

        mAwesomeValidation.addValidation(this, R.id.oil_life, RegexTemplate.NOT_EMPTY, R.string.err_value);
        mAwesomeValidation.addValidation(this, R.id.tires_life, RegexTemplate.NOT_EMPTY, R.string.err_value);
        mAwesomeValidation.addValidation(this, R.id.breaks_life, RegexTemplate.NOT_EMPTY, R.string.err_value);
        mAwesomeValidation.addValidation(this, R.id.air_conditioner_life, RegexTemplate.NOT_EMPTY, R.string.err_value);


        sendbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SendCarInformation();
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view != null && view instanceof EditText) {
                Rect r = new Rect();
                view.getGlobalVisibleRect(r);
                int rawX = (int) ev.getRawX();
                int rawY = (int) ev.getRawY();
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus();
                    hideKeyboard(view);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }


    public void SendCarInformation() {
        if (mAwesomeValidation.validate()) {
            if (Utils.isNetworkConnected(this)) {

                loadingimage.setVisibility(View.VISIBLE);
                dimlayout.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                addcar(vin_number_edittext.getText().toString(), brand_edittext.getText().toString(),
                        modeledittext.getText().toString(), yearedittext.getText().toString(),
                        car_distance_edittext.getText().toString(), last_maintenance_distance_edittext.getText().toString(),
                        last_maintenance_date_edittext.getText().toString(),
                        oil_lifeedittext.getText().toString(),
                        tires_lifeedittext.getText().toString(),
                        breaks_lifeedittext.getText().toString(),
                        air_conditioner_lifeedittext.getText().toString()
                );


            } else {
                loadingimage.setVisibility(View.GONE);
                dimlayout.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                Snackbar snackbar = Snackbar.make(senderrorlayout, R.string.checkconnection, Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setAction(R.string.hide, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Respond to the click, such as by undoing the modification that caused
                                // this message to be displayed
                            }
                        });

                ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                snackbar.show();

            }
        }
    }


    private void addcar(String vinNumber, String Brand, String Model, String Year,
                        String carDistance, String maintenanceDistance, String maintenanceDate,
                        String oilLife, String tiresLife, String breaksLife, String airConditionerLife) {

        Map<String, String> queryـmap = new HashMap<String, String>();

        queryـmap.put("query[action]", "addCar");
        queryـmap.put("query[user_id]", String.valueOf(PrefManager.getUserId(AddCarActivity.this)));
        queryـmap.put("query[vin_number]", vinNumber);
        queryـmap.put("query[brand]", Brand);
        queryـmap.put("query[model]", Model);
        queryـmap.put("query[year]", Year);
        queryـmap.put("query[car_distance]", carDistance);
        queryـmap.put("query[last_m_distance]", maintenanceDistance);
        queryـmap.put("query[last_m_date]", maintenanceDate);

        queryـmap.put("query[oil_life]", oilLife);
        queryـmap.put("query[tires_life]", tiresLife);
        queryـmap.put("query[breaks_life]", breaksLife);
        queryـmap.put("query[air_conditioner_life]", airConditionerLife);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AddCarResponse> call = apiService.addCar(queryـmap);
        call.enqueue(new Callback<AddCarResponse>() {
            @Override
            public void onResponse(Call<AddCarResponse> call, Response<AddCarResponse> response) {

                AddCarResponse addCarResponse = response.body();

                if (response.isSuccessful()) {
                    loadingimage.setVisibility(View.GONE);
                    dimlayout.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    try {

                        if (response.code() == 200) {
                            if (addCarResponse != null) {
                                if (addCarResponse.getStatus().equals("success")) {

                                    PrefManager.setUserLoggedIn(AddCarActivity.this, true);
                                    PrefManager.setCarVinNumber(AddCarActivity.this, addCarResponse.getData().getVin_number());
                                    PrefManager.setCarBrand(AddCarActivity.this, addCarResponse.getData().getBrand());
                                    PrefManager.setCarModel(AddCarActivity.this, addCarResponse.getData().getModel());
                                    PrefManager.setCarYear(AddCarActivity.this, addCarResponse.getData().getYear());
                                    PrefManager.setCarDistance(AddCarActivity.this, addCarResponse.getData().getCar_distance());
                                    PrefManager.setCarLastMDistance(AddCarActivity.this, addCarResponse.getData().getLast_m_distance());
                                    PrefManager.setCarLastMDate(AddCarActivity.this, addCarResponse.getData().getLast_m_date());

                                    PrefManager.setCarOilLife(AddCarActivity.this, addCarResponse.getData().getOil_life());
                                    PrefManager.setCarTiresLife(AddCarActivity.this, addCarResponse.getData().getTires_life());
                                    PrefManager.setCarBreaksLife(AddCarActivity.this, addCarResponse.getData().getBreaks_life());
                                    PrefManager.setCarAirconditionerLife(AddCarActivity.this, addCarResponse.getData().getAir_conditioner_life());

                                    Toast.makeText(AddCarActivity.this, "تم إضافة معلومات السيارة بنجاح", Toast.LENGTH_SHORT).show();

                                    finish();

                                    Intent myIntent = new Intent(AddCarActivity.this, MainActivity.class);
                                    startActivity(myIntent);


                                } else {

                                    Snackbar snackbar = Snackbar.make(senderrorlayout, R.string.error_text, Snackbar.LENGTH_LONG)
                                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                                            .setAction(R.string.hide, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    // Respond to the click, such as by undoing the modification that caused
                                                    // this message to be displayed
                                                }
                                            });
                                    ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                                    snackbar.show();

                                    // if (callbacks != null)
                                    //  callbacks.onFailed(addCarResponse.getMessagesBag());

                                }

                                // }
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }

            @Override
            public void onFailure(Call<AddCarResponse> call, Throwable t) {
                t.printStackTrace();

                loadingimage.setVisibility(View.GONE);
                dimlayout.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }


        });


    }


}
