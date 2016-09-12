package com.ninis.basemvp_sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by gypark on 2016. 9. 8..
 */
public class MainFragment extends Fragment implements MainContract.View {

    @BindView(R.id.tv_response)
    TextView tvResponse;

    private MainContract.Presenter mPresenter;
    private KProgressHUD progressHUD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        tvResponse = (TextView) root.findViewById(R.id.tv_response);

        progressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void showProgress() {
        if( progressHUD != null )
            progressHUD.show();
    }

    @Override
    public void hideProgress() {
        if( progressHUD != null )
            progressHUD.dismiss();
    }

    @Override
    public void setData(String response) {
        try {
//            if( tvResponse != null )
                tvResponse.setText(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
