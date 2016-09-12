package com.ninis.basemvp_sample.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gypark on 2016. 9. 8..
 */
public interface ApiInterface {
    @GET("/posts")
    public Call<ResponseBody> getGeoData();
}
