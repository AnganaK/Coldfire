package com.ekpro.coldfire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ekpro.coldfire.Utils.AbstractBaseActivity;
import com.ekpro.coldfire.webservices.ApiResponse;
import com.ekpro.coldfire.webservices.DepartmentParams;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class FirstTime extends AbstractBaseActivity implements AdapterView.OnItemSelectedListener {

    private Spinner department;
    private Button interest;
    private EditText tags;
    private EditText years;

    private int yearsRemaining;
    private String interestTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_first_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.lightGreen500));
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        department = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);

        department.setAdapter(adapter);
        department.setOnItemSelectedListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();

        service.requestDepartment().enqueue(new Callback<ApiResponse<DepartmentParams>>() {
            @Override
            public void onResponse(Call<ApiResponse<DepartmentParams>> call, retrofit2.Response<ApiResponse<DepartmentParams>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<DepartmentParams> apiResponse = response.body();

                    if (apiResponse.isSuccess()) {
                        DepartmentParams depart = apiResponse.getData();
                        for (int i = 0; i < depart.getDepartments().size(); i++){
                            Log.d("Department", depart.getDepartments().get(i));
                        }
                    } else {

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ApiResponse<DepartmentParams>> call, Throwable t) {
                t.printStackTrace();
                dismissLoadingDialog();
                Toast.makeText(FirstTime.this, getString(R.string.loginerror2), Toast
                        .LENGTH_SHORT).show();
            }
        });


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if(pos == 0){

        } else {

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void ChooseInterests(View v){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Choose any Interests:");
//        alertDialog.setContentView(R.layout.choices);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    public void ActualWork(View v){
        years = (EditText) findViewById(R.id.etYears);
        tags = (EditText) findViewById(R.id.etInterest);
        yearsRemaining = Integer.parseInt(years.getText().toString());

        if (!tags.getText().toString().isEmpty()) {
            interestTags = tags.getText().toString();
        } else {
            Toast err = Toast.makeText(this, "Please enter interests tags.", Toast.LENGTH_SHORT);
            err.show();
        }


    }


}
