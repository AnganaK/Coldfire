package com.ekpro.coldfire;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ekpro.coldfire.Utils.AbstractBaseActivity;
import com.ekpro.coldfire.chapters.ChaptersActivity;
import com.ekpro.coldfire.models.LoginParams;
import com.ekpro.coldfire.models.StudentDetails;
import com.ekpro.coldfire.webservices.ApiResponse;
import com.ekpro.coldfire.webservices.RestClient;

import java.util.HashMap;
import java.util.logging.Logger;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AbstractBaseActivity {

    @Bind(R.id.etUsername)
    EditText etUsername;

    @Bind(R.id.etPassword)
    EditText etPassword;

    @Bind(R.id.btnNext)
    Button btnDiveInNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initToolbar();

        setEditTextChangeListener();
    }

    private void initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.lightGreen500));
        }
    }

    @OnClick(R.id.btnNext)
    public void clickedNext() {


        LoginParams theseParams = new LoginParams();

        theseParams.setUsername(Integer.parseInt(etUsername.getText().toString()));
        theseParams.setPassword(etPassword.getText().toString());

        Log.d("username", String.valueOf(theseParams.getUsername()));
        Log.d("password", theseParams.getPassword());

        if (theseParams.getUsername() <= 100000) {

Log.d("entered here", "value");
        } else {

            Log.d("entered here", "else");

            showLoadingDialog(context, "Requesting", false);

//            TempConsumer tempConsumer = Hawk.get(AppConstant.TEMP_CONSUMER);

//            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//            String imei = telephonyManager.getDeviceId();

//            param.put("IMEI", imei);
//            param.put("TempConsumerID", tempConsumer.getTempConsumerID());
            Log.d("entered here", "value else");

            Log.d("service", service.toString());

            //requestOTPForTempConsumer
            RestClient.instance.getApiService().requestTempStudentLogin(theseParams).enqueue(new Callback<ApiResponse<StudentDetails>>() {
                @Override
                public void onResponse(Call<ApiResponse<StudentDetails>> call, retrofit2.Response<ApiResponse<StudentDetails>> response) {

//                    Logger.d(gson.toJson(response));

                    Log.d("service", "onResponse: ");

                    dismissLoadingDialog();

                    if (response.isSuccessful()) {
                        ApiResponse<StudentDetails> apiResponse = response.body();

                        if (apiResponse.isSuccess()) {

                            StudentDetails stu = apiResponse.getData();

                            if ( stu.getReturning() ) {
                                startActivity(new Intent(LoginActivity.this, ChaptersActivity.class));
                                finish();
                            } else{

                                startActivity(new Intent(LoginActivity.this, FirstTime.class));
                                finish();
                            }

//                            overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
                        } else {
                            Toast.makeText(LoginActivity.this, apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                            Utilities.instance.shortSnack(rlContainer, "Failed to send OTP");
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.loginerror), Toast
                                .LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<StudentDetails>> call, Throwable t) {
                    t.printStackTrace();
                    dismissLoadingDialog();
                    Toast.makeText(LoginActivity.this, getString(R.string.loginerror2), Toast
                            .LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setEditTextChangeListener() {

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String username = etUsername.getText().toString().trim();

                if (username.length() == 9) {

                    btnDiveInNext.setVisibility(View.VISIBLE);

                    hideKeyboard();

                } else {

                    btnDiveInNext.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String password = etPassword.getText().toString().trim();

                btnDiveInNext.setVisibility(View.VISIBLE);

//                hideKeyboard();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(etUsername.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS, 0);
    }
}
