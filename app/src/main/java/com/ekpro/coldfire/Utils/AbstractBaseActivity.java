package com.incredible.computerstudies.Utils;


import android.app.Dialog;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.incredible.computerstudies.R;
import com.incredible.computerstudies.webservices.ApiService;
import com.incredible.computerstudies.webservices.RestClient;

import java.util.Date;

import butterknife.ButterKnife;

/**
 * Created by incredible on 3/7/16.
 */
public class AbstractBaseActivity extends AppCompatActivity {
    protected Context context;
    protected ApiService service;
    protected Gson gson;
    public Dialog dialogConfirmation;
//    public Servify servify = Servify.getSharedInstance();
    private Dialog loadingDialog;

    /*Keen Project Data*/
    public static final String CONSUMER_PROJECT_ID = "56fb85cd672e6c02b90521b4";
    public static final String CONSUMER_WRITE_KEY = "74b0855b69a35b2f37f6c78b3effda08f606263fbd363977c3019a32455d649cd157c3bef3ba78f3309a2b02613643791b67abad51c8bb52104656b3ae9f05622c6b604d8540a9d024878d12cacc3900111de55de3f3e01ecdc59a9e88aad0be";
    public static final String CONSUMER_READ_KEY = "75401ff7046044acf44e8f0de30379b24e1e250988e139c92ec123eb0963f38c5ed5ee3fce28caf436c41ef9a506abe9a85abc26e60079289777b8f5eb3d53ad18f6af3988a32d0fbc7dc75ff3bf50fc94961c45717d2b3ff570c094e6a34823";
//    public static final KeenProject consumerProject = new KeenProject(CONSUMER_PROJECT_ID, CONSUMER_WRITE_KEY, CONSUMER_READ_KEY);
    private long seconds;
    public Date startRead, stopRead;
    public int difference;
//    public Consumer consumer;

//    public AppPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        gson = new Gson();

        service = RestClient.instance.getApiService();

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);


        ButterKnife.bind(this);
    }


        @Override
        protected void onResume() {
            super.onResume();
            startRead = new Date();
            showNetworkDialog();
        }

        @Override
        protected void attachBaseContext(Context newBase) {
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                onBackPressed();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


        @Override
        protected void onPause() {
            super.onPause();

            if (!isFinishing()) {
                dismissLoadingDialog();

//        dismissLoadingDialog();
                if (dialogConfirmation != null && dialogConfirmation.isShowing()) {
                    dialogConfirmation.dismiss();
                }
            }
        }

        public boolean isNetworkOnline() {
            boolean status = false;

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (mWifi.isConnected()) {
                return true;
            }

            try {
                NetworkInfo netInfo = connManager.getNetworkInfo(0);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    status = true;
                } else {
                    netInfo = connManager.getNetworkInfo(1);
                    if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                        status = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return status;
        }

        private void showNetworkDialog() {
//            if (!isNetworkOnline()) {
//
//                setNetworkDialog();
//            }
        }

        public boolean isGpsEnabled() {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        public void setNetworkDialog() {

            dialogConfirmation = new Dialog(context, R.style.AppTheme);
            dialogConfirmation.setContentView(R.layout.dialog_confirmation_item);
            dialogConfirmation.setCancelable(false);

            TextView tvDialogContent = (TextView) dialogConfirmation.findViewById(R.id.tvDialogContent);
            TextView tvDialogTitle = (TextView) dialogConfirmation.findViewById(R.id.tvDialogTitle);
            ImageView ivDialogIcon = (ImageView) dialogConfirmation.findViewById(R.id.ivDialogIcon);
            Button btnCommon = (Button) dialogConfirmation.findViewById(R.id.btnAllow);
            Button btnSkip = (Button) dialogConfirmation.findViewById(R.id.btnSkip);
            tvDialogTitle.setText("OH NO!");
            tvDialogContent.setText("We couldn't detect a network connection. :(");
            btnCommon.setText("OK");
            btnSkip.setVisibility(View.GONE);
//            ivDialogIcon.setImageResource(R.drawable.ic_drawer);

            btnCommon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if yes
                    dialogConfirmation.dismiss();
//                    showNetworkDialog();
                }
            });

            btnSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if no
                }
            });

            dialogConfirmation.show();
        }

        public void showLoadingDialog(Context context, String message, boolean isCancellable) {

            loadingDialog = new Dialog(context, R.style.AppTheme_AppBarOverlay);
            loadingDialog.setContentView(R.layout.loading_dialog);
            loadingDialog.setCanceledOnTouchOutside(false);

            if (isCancellable) {
                loadingDialog.setCancelable(true);
            } else {
                loadingDialog.setCancelable(false);
            }

            TextView tvLoadingText = (TextView) loadingDialog.findViewById(R.id.tvLoadingText);

            if (message != null && !message.isEmpty()) {
                tvLoadingText.setVisibility(View.VISIBLE);
                tvLoadingText.setText(message);
            } else {
                tvLoadingText.setVisibility(View.GONE);
            }

            if(!isFinishing()) {
                loadingDialog.show();
            }
        }

        public void dismissLoadingDialog() {

            if (loadingDialog != null && loadingDialog.isShowing()) {
                if(!isFinishing()) {
                    loadingDialog.dismiss();
                }
            }
        }
}
