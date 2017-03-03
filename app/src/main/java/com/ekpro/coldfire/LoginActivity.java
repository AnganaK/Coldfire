package com.ekpro.coldfire;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ekpro.coldfire.Utils.AbstractBaseActivity;
import com.ekpro.coldfire.chapters.ChaptersActivity;
import com.ekpro.coldfire.models.StudentDetails;
import com.ekpro.coldfire.webservices.ApiResponse;

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


        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        if (username.isEmpty()) {


        } else {


            showLoadingDialog(context, "Requesting OTP", false);

//            TempConsumer tempConsumer = Hawk.get(AppConstant.TEMP_CONSUMER);

//            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//            String imei = telephonyManager.getDeviceId();

            HashMap<String, Object> param = new HashMap<>();
            param.put("username", username);
            param.put("password", password);
//            param.put("IMEI", imei);
//            param.put("TempConsumerID", tempConsumer.getTempConsumerID());

            //requestOTPForTempConsumer
            service.requestTempStrudentLogin(param).enqueue(new Callback<ApiResponse<StudentDetails>>() {
                @Override
                public void onResponse(Call<ApiResponse<StudentDetails>> call, retrofit2.Response<ApiResponse<StudentDetails>> response) {

//                    Logger.d(gson.toJson(response));

                    dismissLoadingDialog();

                    if (response.isSuccessful()) {
                        ApiResponse<StudentDetails> apiResponse = response.body();

                        if (apiResponse.isSuccess()) {


                            startActivity(new Intent(LoginActivity.this, ChaptersActivity.class));
                            finish();

//                            overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
                        } else {
                            Toast.makeText(LoginActivity.this, apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                            Utilities.instance.shortSnack(rlContainer, "Failed to send OTP");
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "something went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<StudentDetails>> call, Throwable t) {
                    dismissLoadingDialog();
                    Toast.makeText(LoginActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
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
