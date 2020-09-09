package com.creative.automc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.creative.automc.APIHandler.DatabaseInterface;
import com.creative.automc.APIHandler.Response.LoginResponse;
import com.creative.automc.R;
import com.creative.automc.parser.CoreParser;
import com.creative.automc.utils.PrefManager;
import com.creative.automc.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class SignUpActivity extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1;
    AwesomeValidation mAwesomeValidation;
    ProgressBar loadingimage;
    Button signupbtn;
    EditText firstnameedittext, lastnameedittext, emailedittext, locationedittext, passwordedittext, confirmpasswordedittext;

    TextInputLayout firstName, lastname, emailtextinput, location, password, confirmpassword;

    RelativeLayout dimlayout, signuplayout;
    Spinner citySpinner;
    String citySpinnerTxt = "Abha";


    public static void start(Context context) {
        Intent starter = new Intent(context, SignUpActivity.class);

        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        loadingimage = findViewById(R.id.loadingimage);
        dimlayout = findViewById(R.id.dimlayout);
        signuplayout = findViewById(R.id.signuplayout);

        firstName = findViewById(R.id.fisrtName);
        lastname = findViewById(R.id.lastname);
        emailtextinput = findViewById(R.id.emailtextinput);
        location = findViewById(R.id.location);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);

        firstnameedittext = findViewById(R.id.fisrtnameedittext);
        lastnameedittext = findViewById(R.id.lastnameedittext);
        emailedittext = findViewById(R.id.emailedittext);
        locationedittext = findViewById(R.id.locationedittext);
        passwordedittext = findViewById(R.id.passwordedittext);
        confirmpasswordedittext = findViewById(R.id.confirmpasswordedittext);


        citySpinner = findViewById(R.id.location_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                citySpinnerTxt = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

/*
        citySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });*/


        signupbtn = findViewById(R.id.signupbtn);

        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
        // setUnderlabelColor is optional for UNDERLABEL style, by default it's holo_red_light

        mAwesomeValidation.setUnderlabelColorByResource(android.R.color.holo_red_dark); // optional for UNDERLABEL style

        mAwesomeValidation.addValidation(this, R.id.fisrtName, RegexTemplate.NOT_EMPTY, R.string.err_fnameedittext);
        mAwesomeValidation.addValidation(this, R.id.lastname, RegexTemplate.NOT_EMPTY, R.string.err_lnameedittext);
        mAwesomeValidation.addValidation(this, R.id.emailtextinput, Patterns.EMAIL_ADDRESS, R.string.err_name);
        // mAwesomeValidation.addValidation(this, R.id.location, RegexTemplate.NOT_EMPTY, R.string.err_location);
        mAwesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY, R.string.err_password);
        mAwesomeValidation.addValidation(this, R.id.confirmpassword, RegexTemplate.NOT_EMPTY, R.string.err_cpassword);


        passwordedittext.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);


        confirmpasswordedittext.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);


        signupbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SingUp();
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();

        try {
            this.getSupportActionBar().setTitle(R.string.signup);

        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public void SingUp() {
        if (mAwesomeValidation.validate()) {
            if (!passwordedittext.getText().toString().trim().equals(confirmpasswordedittext.getText().toString().trim())) {

                Toast.makeText(SignUpActivity.this, "Password Not Matched", Toast.LENGTH_LONG).show();
            } else {


                if (Utils.isNetworkConnected(this)) {

                    if (CoreParser.signup(this, firstnameedittext.getText().toString(), lastnameedittext.getText().toString(), emailedittext.getText().toString(),
                            citySpinnerTxt, passwordedittext.getText().toString(), new DatabaseInterface() {
                                @Override
                                public void onSuccess(@NonNull Boolean value) {

                                    if (value) {

                                        PrefManager.setUserLoggedIn(SignUpActivity.this, true);
                                        PrefManager.setUserFullName(SignUpActivity.this, firstnameedittext.getText().toString());
                                        PrefManager.setUserEmail(SignUpActivity.this, emailedittext.getText().toString());
                                        PrefManager.setLoggedPassword(SignUpActivity.this, passwordedittext.getText().toString());

                                        // PrefUtils.putString(PrefUtils.FRAGMENT_SCREEN, PrefUtils.FRAGMENT_PROFILE);

                                        Toast.makeText(SignUpActivity.this, getResources().getString(R.string.signup_successfully), Toast.LENGTH_SHORT).show();

                                        finish();
                                        Intent myIntent = new Intent(SignUpActivity.this, AddCarActivity.class);
                                        startActivity(myIntent);


                                    } else {
                                        Snackbar snackbar = Snackbar.make(signuplayout, R.string.error_text, Snackbar.LENGTH_LONG)
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

                                @Override
                                public void onFailed(@NonNull LoginResponse.MessagesBag value) {

                                    loadingimage.setVisibility(View.GONE);
                                    dimlayout.setVisibility(View.GONE);
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                    if (value.getEmail() != null) {

                                        String errormsg;
                                        if (value.getEmail().get(0).equals("validation.unique")) {
                                            errormsg = "عذراً، هذا البريد الاليكتروني موجود بالفعل";
                                        } else {
                                            errormsg = value.getEmail().get(0);
                                        }


                                        Snackbar snackbar = Snackbar.make(signuplayout, errormsg, Snackbar.LENGTH_LONG)
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
                                    } else if (value.getPassword() != null) {
                                        Snackbar snackbar = Snackbar.make(signuplayout, "يجب الا تقل كلمة المرور عن 8 رموز", Snackbar.LENGTH_LONG)
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

                                @Override
                                public void onError(@NonNull Throwable throwable) {

                                    loadingimage.setVisibility(View.GONE);
                                    dimlayout.setVisibility(View.GONE);
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                    Snackbar snackbar = Snackbar.make(signuplayout, R.string.error_text, Snackbar.LENGTH_LONG)
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
                            })) ;


                } else {
                    loadingimage.setVisibility(View.GONE);
                    dimlayout.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    Snackbar snackbar = Snackbar.make(signuplayout, R.string.checkconnection, Snackbar.LENGTH_LONG)
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
    }

}
