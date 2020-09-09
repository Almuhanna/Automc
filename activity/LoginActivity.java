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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.creative.automc.APIHandler.DatabaseInterfacee;
import com.creative.automc.APIHandler.Response.LoginResponse;
import com.creative.automc.APIHandler.Response.LoginWithCarResponse;
import com.creative.automc.R;
import com.creative.automc.parser.CoreParser;
import com.creative.automc.utils.MainApplication;
import com.creative.automc.utils.PrefManager;
import com.creative.automc.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class LoginActivity extends AppCompatActivity {

    AwesomeValidation mAwesomeValidation;


    ProgressBar loadingimage;
    RelativeLayout dimlayout, loginlayout;
    EditText usernameedittext;
    EditText passwordedittext;
    TextInputLayout password;


    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);

        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Button loginbtn = findViewById(R.id.loginbtn);

        TextView create_account = findViewById(R.id.create_account);

        dimlayout = findViewById(R.id.dimlayout);

        loginlayout = findViewById(R.id.loginlayout);
        loadingimage = findViewById(R.id.loadingimage);


        usernameedittext = findViewById(R.id.usernameedittext);
        passwordedittext = findViewById(R.id.passwordedittext);
        password = findViewById(R.id.password);


        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);  // mandatory for UNDERLABEL style
        // setUnderlabelColor is optional for UNDERLABEL style, by default it's holo_red_light

        mAwesomeValidation.setUnderlabelColorByResource(R.color.colorAccent); // optional for UNDERLABEL style


        mAwesomeValidation.addValidation(this, R.id.username, RegexTemplate.NOT_EMPTY, R.string.err_email);
        mAwesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY, R.string.err_password);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginfun();
            }
        });


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.start(LoginActivity.this);
            }
        });


    }

    private void loginfun() {
        if (mAwesomeValidation.validate()) {
            if (Utils.isNetworkConnected(this)) {

                loadingimage.setVisibility(View.VISIBLE);
                dimlayout.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                if (CoreParser.login(LoginActivity.this, usernameedittext.getText().toString(), passwordedittext.getText().toString(), new DatabaseInterfacee() {
                    @Override
                    public void onSuccess(@NonNull Boolean value) {

                        loadingimage.setVisibility(View.GONE);
                        dimlayout.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        if (value) {

                            PrefManager.setUserLoggedIn(LoginActivity.this, true);


                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_successfully), Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailed(@NonNull LoginWithCarResponse.MessagesBag value) {

                        loadingimage.setVisibility(View.GONE);
                        dimlayout.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        Snackbar snackbar = Snackbar.make(loginlayout, value.getSuccess_process().get(0), Snackbar.LENGTH_LONG)
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

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                        loadingimage.setVisibility(View.GONE);
                        dimlayout.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(LoginActivity.this, R.string.error_text, Toast.LENGTH_SHORT).show();

                    }
                })) ;
            } else {
                loadingimage.setVisibility(View.GONE);
                dimlayout.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(LoginActivity.this, R.string.checkconnection, Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();

        try {
            this.getSupportActionBar().setTitle(R.string.login);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {
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
        loadingimage.setVisibility(View.GONE);
        dimlayout.setVisibility(View.GONE);
//        PrefUtils.putString(PrefUtils.FRAGMENT_SCREEN, PrefUtils.FRAGMENT_PROFILE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        // MainActivity.start(this);
        finish();
    }


}
