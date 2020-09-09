package com.creative.automc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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
import com.creative.automc.APIHandler.Response.BaseResponse;
import com.creative.automc.R;
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

public class EditCarActivity extends AppCompatActivity {

    static String action_txt;
    static String EditText_txt;
    AwesomeValidation mAwesomeValidation;
    ProgressBar loadingimage;
    Button sendbtn;
    EditText edittext_action;
    TextInputLayout editValue;
    RelativeLayout dimlayout, senderrorlayout;

    public static void start(Context context, String text, String action) {
        Intent starter = new Intent(context, EditCarActivity.class);
        action_txt = action;
        EditText_txt = text;
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);


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

        edittext_action = findViewById(R.id.last_m_distance_edittext);
        editValue = findViewById(R.id.edit_value);

        edittext_action.setHint("Type " + action_txt);


        edittext_action.setText(EditText_txt);


        sendbtn = findViewById(R.id.sendbtn);

        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
        // setUnderlabelColor is optional for UNDERLABEL style, by default it's holo_red_light

        mAwesomeValidation.setUnderlabelColorByResource(android.R.color.holo_red_dark); // optional for UNDERLABEL style

        mAwesomeValidation.addValidation(this, R.id.edit_value, RegexTemplate.NOT_EMPTY, R.string.err_last_date);


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


                addcar(edittext_action.getText().toString());


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


    private void addcar(final String action) {

        Map<String, String> queryـmap = new HashMap<String, String>();

        queryـmap.put("query[action]", "updateCarState");
        queryـmap.put("query[user_id]", String.valueOf(PrefManager.getUserId(EditCarActivity.this)));

        queryـmap.put("query[" + action_txt + "]", action);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BaseResponse> call = apiService.editCar(queryـmap);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                BaseResponse addCarResponse = response.body();

                if (response.isSuccessful()) {
                    loadingimage.setVisibility(View.GONE);
                    dimlayout.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    try {

                        if (response.code() == 200) {
                            if (addCarResponse != null) {
                                if (addCarResponse.getStatus().equals("success")) {


                                    if (action_txt.equals("car_distance"))
                                        PrefManager.setCarDistance(EditCarActivity.this, action);
                                    else if (action_txt.equals("maintenance_distance"))
                                        PrefManager.setCarLastMDistance(EditCarActivity.this, action);
                                    else if (action_txt.equals("oil_life"))
                                        PrefManager.setCarOilLife(EditCarActivity.this, action);
                                    else if (action_txt.equals("tires_life"))
                                        PrefManager.setCarTiresLife(EditCarActivity.this, action);
                                    else if (action_txt.equals("breaks_life"))
                                        PrefManager.setCarBreaksLife(EditCarActivity.this, action);
                                    else if (action_txt.equals("air_conditioner_life"))
                                        PrefManager.setCarAirconditionerLife(EditCarActivity.this, action);


                                    Toast.makeText(EditCarActivity.this, "تم تعديل معلومات السيارة بنجاح", Toast.LENGTH_SHORT).show();

                                    finish();

                                    Intent myIntent = new Intent(EditCarActivity.this, MainActivity.class);
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
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();

                loadingimage.setVisibility(View.GONE);
                dimlayout.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }


        });


    }


}
