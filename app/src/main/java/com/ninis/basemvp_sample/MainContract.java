package com.ninis.basemvp_sample;

import com.ninis.basemvp_sample.base.BasePresenter;
import com.ninis.basemvp_sample.base.BaseView;

import okhttp3.ResponseBody;

/**
 * Created by gypark on 2016. 9. 8..
 */
public class MainContract  {

    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void setData(String response);
    }

    interface Presenter extends BasePresenter {

    }
}
