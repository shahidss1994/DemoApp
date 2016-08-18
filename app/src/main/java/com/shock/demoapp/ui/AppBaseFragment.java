package com.shock.demoapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by shahid on 12/8/16.
 */
public class AppBaseFragment extends Fragment {

    protected final int LENGTH_SHORT = Snackbar.LENGTH_SHORT;
    protected final int LENGTH_LONG = Snackbar.LENGTH_LONG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void startActivity(Intent intent, final boolean finishActivity) {
        ((AppBaseActivity) getActivity()).startActivity(intent, finishActivity);
    }

    protected void showProgressDailog() {
        ((AppBaseActivity) getActivity()).showProgressDailog();
    }

    protected void hideProgressDailog() {
        ((AppBaseActivity) getActivity()).hideProgressDailog();
    }

    protected void showToast(String msg, int length) {
        ((AppBaseActivity) getActivity()).showToast(msg, length);
    }

    protected void replaceFragment(int layoutId, Fragment fragment, String tag) {
        ((AppBaseActivity) getActivity()).replaceFragment(layoutId, fragment, tag);
    }
}
