package com.example.velm.domainsearchmvp.data.api;

import android.util.Log;

import com.example.velm.domainsearchmvp.model.Domains;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by velmmuru on 10/11/2017.
 */

public class AppApiImpl {

    Domains domains;

    public Domains getDomains(String url){

        domains = null;
        AppApi appApi = ApiClient.getClient().create(AppApi.class);

        Call call1 = appApi.getDomains(url);

        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                domains = (Domains) response.body();
                Log.d(TAG,domains.toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("Response",t.toString());
            }
        });

        return domains;
    };

}
