package com.incredible.computerstudies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.incredible.computerstudies.Utils.AbstractBaseActivity;
import com.incredible.computerstudies.chapters.ChaptersActivity;

import butterknife.OnClick;

public class StanderdActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standerd);
    }

    @OnClick(R.id.btSTDeight)
    public void eightStaderd(){
//        Snackbar snackbar = new Snackbar();
        Toast.makeText(StanderdActivity.this, "This feature is coming soon!! stay connected:)", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btSTDnine)
    public void ninthStaderd(){
//        Snackbar snackbar = new Snackbar();
        Toast.makeText(StanderdActivity.this, "This feature is coming soon!! stay connected:)", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btSTDten)
    public void tenthStaderd(){
//        Snackbar snackbar = new Snackbar();
//        Toast.makeText(StanderdActivity.this, "This feature is coming soon!! stay connected:)", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChaptersActivity.class);
        intent.putExtra(AppConstant.STD, 10);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btSTDeleven)
    public void eleventhStaderd(){
//        Snackbar snackbar = new Snackbar();
        Toast.makeText(StanderdActivity.this, "This feature is coming soon!! stay connected:)", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btSTDtwelve)
    public void twelthStaderd(){
//        Snackbar snackbar = new Snackbar();
        Toast.makeText(StanderdActivity.this, "This feature is coming soon!! stay connected:)", Toast.LENGTH_SHORT).show();
    }

}
