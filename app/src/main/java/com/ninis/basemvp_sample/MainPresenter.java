package com.ninis.basemvp_sample;

import android.support.annotation.NonNull;

import com.ninis.basemvp_sample.network.RetrofitManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by gypark on 2016. 9. 8..
 */
public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;

    public MainPresenter(@NonNull MainContract.View mainView) {
        mMainView = mainView;
        mMainView.setPresenter(this);
    }

    /**
     * Api Call
     */
    @Override
    public void start() {
        mMainView.showProgress();

        RetrofitManager.getInstance().getApiInterface().getGeoData().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
//                    Log.d("NINIS", "onResponse | " + response.body().string());
                    mMainView.setData(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mMainView.hideProgress();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mMainView.hideProgress();
            }
        });
    }
}
