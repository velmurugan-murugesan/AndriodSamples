package com.example.velm.domainsearchmvp.data.api;

import com.example.velm.domainsearchmvp.model.Domains;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by velmmuru on 9/17/2017.
 */

public interface AppApi {

    @GET("domainStatus")
    Call<Domains> getDomains(@Query("domains") String domains);
}
