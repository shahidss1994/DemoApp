package com.shock.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by shahid on 12/8/16.
 */
public class AppBaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startActivity(Intent intent, final boolean finishActivity) {
        ((AppBaseActivity) getActivity()).startActivity(intent, finishActivity);
    }

    public void showProgressDailog() {
        ((AppBaseActivity) getActivity()).showProgressDailog();
    }

    public void hideProgressDailog() {
        ((AppBaseActivity) getActivity()).hideProgressDailog();
    }
}
