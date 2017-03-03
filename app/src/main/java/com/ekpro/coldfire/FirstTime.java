package com.ekpro.coldfire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.ekpro.coldfire.Utils.AbstractBaseActivity;
import com.ekpro.coldfire.chapters.ChaptersActivity;
import com.ekpro.coldfire.webservices.ApiResponse;
import com.ekpro.coldfire.webservices.DepartmentParams;
import com.ekpro.coldfire.webservices.InterestParams;
import com.ekpro.coldfire.webservices.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class FirstTime extends AbstractBaseActivity implements AdapterView.OnItemSelectedListener {

    DepartmentParams depart;
    InterestParams inter;

    private Spinner department;
    private Button interest;
    private EditText tags;
    private EditText years;
    private Button next;

    private int yearsRemaining;
    private String interestTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_first_time);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.lightGreen500));
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

        department = (Spinner) findViewById(R.id.spinner);
        interest = (Button) findViewById(R.id.button2);
        tags = (EditText) findViewById(R.id.etInterest);
        years = (EditText) findViewById(R.id.etYears);
        next = (Button) findViewById(R.id.btnNext);

        interest.setVisibility(View.GONE);
        tags.setVisibility(View.GONE);
        years.setVisibility(View.GONE);
        next.setVisibility(View.GONE);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        RestClient.instance.getApiService().requestDepartment().enqueue(new
            Callback<ApiResponse<DepartmentParams>>() {
                @Override
                public void onResponse(Call<ApiResponse<DepartmentParams>> call, retrofit2.Response<ApiResponse<DepartmentParams>> response) {
                    if (response.isSuccessful()) {
                        ApiResponse<DepartmentParams> apiResponse = response.body();

                        if (apiResponse.isSuccess()) {
                            depart = apiResponse.getData();
//                            for (int i = 0; i < depart.getDepartments().size(); i++){
//                                Log.d("Department", depart.getDepartments().get(i));
//                            }

                            setAdapter();

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

    public void setAdapter(){
        department = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_item, depart.getDepartments());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(dataAdapter);
        department.setOnItemSelectedListener(FirstTime.this);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                depart, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        requestInterest(depart.getDepartments().get(pos));
    }


    private void requestInterest(String department) {
        HashMap<String, String> params = new HashMap<>();
        params.put("department", department);

        RestClient.instance.getApiService().requestInterest(params).enqueue(new
            Callback<ApiResponse<InterestParams>>() {
                @Override
                public void onResponse(Call<ApiResponse<InterestParams>> call, retrofit2.Response<ApiResponse<InterestParams>> response) {
                    if (response.isSuccessful()) {
                        ApiResponse<InterestParams> apiResponse = response.body();

                        if (apiResponse.isSuccess()) {
                            inter = apiResponse.getData();
    //                            for (int i = 0; i < depart.getDepartments().size(); i++){
    //                                Log.d("Department", depart.getDepartments().get(i));
    //                            }
                            interest.setVisibility(View.VISIBLE);

//                            setAdapter();

                        } else {

                        }
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<InterestParams>> call, Throwable t) {
                    t.printStackTrace();
                    dismissLoadingDialog();
                    Toast.makeText(FirstTime.this, getString(R.string.servererror), Toast
                            .LENGTH_SHORT).show();
                }
            });
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void ChooseInterests(View v){
//        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle("Choose any Interests:");
//
//        LinearLayout choices = new LinearLayout(this);
//        LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        choices.setLayoutParams(LLParams);
//        int n = 5;
//        CheckBox[] fields = new CheckBox[n];
//        for ( int i = 0; i < n; i++) {
//            fields[i] = new CheckBox(this);
//            fields[i].setText("WOW!");
//            choices.addView(fields[i]);
//        }
//
//        alertDialog.setContentView(choices);
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        alertDialog.show();

        CharSequence[] items = new CharSequence[inter.getInterest().size()];
        for (int i = 0; i < inter.getInterest().size(); i++){
            items[i] = inter.getInterest().get(i).getCourse_name();
        }
//        final CharSequence[] items = {" Easy "," Medium "," Hard "," Very Hard "};
        // arraylist to keep the selected items
        final ArrayList seletedItems=new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select your Interests:")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener
                        () {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        tags.setVisibility(View.VISIBLE);
                        years.setVisibility(View.VISIBLE);
                        next.setVisibility(View.VISIBLE);

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).create();
        dialog.show();
    }

    public void ActualWork(View v){
        years = (EditText) findViewById(R.id.etYears);
        tags = (EditText) findViewById(R.id.etInterest);

        boolean error = false;

        if (!tags.getText().toString().isEmpty()) {
            interestTags = tags.getText().toString();
            error = false;
        } else {
            Toast err = Toast.makeText(this, "Please enter interests tags.", Toast.LENGTH_SHORT);
            err.show();
            error = true;
        }

        if (!years.getText().toString().isEmpty()){
            yearsRemaining = Integer.parseInt(years.getText().toString());
            error = false;
        } else {
            Toast err = Toast.makeText(this, "Please enter the number of remaining years.",
                    Toast.LENGTH_SHORT);
            err.show();
            error = true;
        }

        if(!error){
            startActivity(new Intent(FirstTime.this, ChaptersActivity.class));
            finish();
        }




    }


}
