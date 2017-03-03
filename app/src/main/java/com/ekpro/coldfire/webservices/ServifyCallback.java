package com.ekpro.coldfire.webservices;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dakshal on 18/11/15.
 */
public abstract class ServifyCallback<T> implements retrofit2.Callback<T> {

    private static final String TAG = "ServifyCallback";
    private Context context;
    private Gson gson;

    public ServifyCallback(Context c) {
        this.context = c;

        gson = new Gson();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()){
            done(response.body(), null);
        } else {
            Log.d(TAG, "onResponse: failed");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        done(null, t);
    }

/*
    @Override
    public void success(T t, Response response) {
        done(t, null);
    }

    @Override
    public void failure(Error error) {

       */
/* AbstractBaseActivity activity = (AbstractBaseActivity) context;
        Intent intent = new Intent(activity, NoInternet.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);*//*


//        Toast.makeText(context,"error on servify callback",Toast.LENGTH_SHORT).show();
        done(null, error);

    }
*/

    public abstract void done(T t, Throwable error);

}
