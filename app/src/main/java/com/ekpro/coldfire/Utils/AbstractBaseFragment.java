package com.ekpro.coldfire.Utils;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekpro.coldfire.R;
import com.ekpro.coldfire.webservices.ApiService;
import com.ekpro.coldfire.webservices.RestClient;

import java.util.Date;

import butterknife.ButterKnife;

/**
 * Created by yash on 04/11/15.
 */
public abstract class AbstractBaseFragment extends Fragment {

    protected Context context;
    protected ApiService service;
    protected FragmentTransacListener fragmentTransacListener;
    protected FragmentAttachListener fragmentAttachListener;
    private Dialog dialogConfirmation;
    private String TAG = "AbstractBaseFragment";

//    public Servify servify = Servify.getSharedInstance();
    public boolean isTemp;
//    public Gson gson;
    private Dialog loadingDialog;

    /*Keen Project Data*/
    public static final String CONSUMER_PROJECT_ID = "56fb85cd672e6c02b90521b4";
    public static final String CONSUMER_WRITE_KEY = "74b0855b69a35b2f37f6c78b3effda08f606263fbd363977c3019a32455d649cd157c3bef3ba78f3309a2b02613643791b67abad51c8bb52104656b3ae9f05622c6b604d8540a9d024878d12cacc3900111de55de3f3e01ecdc59a9e88aad0be";
    public static final String CONSUMER_READ_KEY = "75401ff7046044acf44e8f0de30379b24e1e250988e139c92ec123eb0963f38c5ed5ee3fce28caf436c41ef9a506abe9a85abc26e60079289777b8f5eb3d53ad18f6af3988a32d0fbc7dc75ff3bf50fc94961c45717d2b3ff570c094e6a34823";
//    public static final KeenProject consumerProject = new KeenProject(CONSUMER_PROJECT_ID, CONSUMER_WRITE_KEY, CONSUMER_READ_KEY);
    private long seconds;
    public Date startRead, stopRead;
    public int difference;
//    public AppPreferences prefs;
//    public Consumer consumer;
    /*Keen Project Data*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        startRead = new Date();
        showNetworkDialog();

        service = RestClient.instance.getApiService();

    }

    @Override
    public void onPause() {
        super.onPause();
        dismissLoadingDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateKnifeView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    public abstract View onCreateKnifeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
//        service = RestClient.instance.getApiService();
//        gson = new Gson();
//        prefs = new AppPreferences(context);
//        consumer = Hawk.get(AppConstant.CONSUMER);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (activity instanceof FragmentTransacListener) {
            fragmentTransacListener = (FragmentTransacListener) activity;
        }

        if (activity instanceof FragmentAttachListener) {
            fragmentAttachListener = (FragmentAttachListener) activity;
            if (fragmentAttachListener != null) {
                fragmentAttachListener.onAttached(this);

            }
        }
    }

    protected void startFragment(AbstractBaseFragment baseFragment) {
        if (fragmentTransacListener != null) {
            fragmentTransacListener.requestTransaction(baseFragment, false);
        }
    }

    protected void replaceFragment(AbstractBaseFragment baseFragment) {
        if (fragmentTransacListener != null) {
            fragmentTransacListener.requestTransaction(baseFragment, true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract String getTitle();


    public void animate(AbstractBaseFragment second) {

    }

    public boolean onBackPressed() {
        return false;
    }

    public interface FragmentTransacListener {
        void requestTransaction(AbstractBaseFragment baseFragment, boolean shouldReplace);
    }

    public interface FragmentAttachListener {
        void onAttached(AbstractBaseFragment fragment);
    }

    public boolean isNetworkOnline() {
        boolean status = false;

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public void setScreenImpression(String screenName) {

//        if (!BuildConfig.DEBUG) {
//            ServifyApp application = (ServifyApp) getActivity().getApplication();
//            Tracker mTracker = application.getDefaultTracker();
//
//            mTracker.enableAdvertisingIdCollection(true);
//            mTracker.setScreenName("" + screenName);
//            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
//
//            PackageInfo pInfo = null;
//
//            try {
//                pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
//
//                String version = pInfo.versionName;
//                mTracker.setAppVersion(version);
//
//                int verCode = pInfo.versionCode;
//                Log.d(TAG, "verCode " + verCode);
//
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }


    public void setNetworkDialog() {

        dialogConfirmation = new Dialog(context, R.style.AppTheme_NoActionBar);
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
//        ivDialogIcon.setImageResource(R.drawable.ic_drawer);

        btnCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if yes
                dialogConfirmation.dismiss();
//                showNetworkDialog();


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

    private void showNetworkDialog() {
//        if (!isNetworkOnline()) {
//            setNetworkDialog();
//        }
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

        if(!getActivity().isFinishing()) {
            loadingDialog.show();
        }
    }

    public void dismissLoadingDialog() {

        if (loadingDialog != null && loadingDialog.isShowing()) {
            if(!getActivity().isFinishing()) {
                loadingDialog.dismiss();
            }
        }
    }

}
