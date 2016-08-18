package com.shock.demoapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.shock.demoapp.R;
import com.shock.demoapp.di.DemoApp;

import butterknife.BindView;

/**
 * Created by shahid on 10/8/16.
 */
public class AppBaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    private ProgressDialog progressDialog;
    private DemoApp demoApp;
    private Context context;
    protected FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demoApp = DemoApp.get();
        context = demoApp.getApplicationContext();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
    }

    protected void setToolbar(String title, boolean showBackButton) {
        try {
            toolbar.setTitle(title == null ? getResources().getString(R.string.app_name) : title);
            setSupportActionBar(toolbar);
            if (showBackButton) {
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startActivity(Intent intent, final boolean finishActivity) {
        startActivity(intent);
        if (finishActivity)
            finish();
    }

    protected void addFragment(int layoutId, Fragment fragment,String tag) {
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    protected void replaceFragment(int layoutId, Fragment fragment,String tag) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.commit();
    }

    public void removeFragment(){
        getSupportFragmentManager().popBackStackImmediate();
    }

    protected void showProgressDailog() {
        progressDialog = new ProgressDialog(AppBaseActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading_please_wait_msg));
        progressDialog.show();
    }

    protected void hideProgressDailog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void showToast(String msg, int length) {
        Toast.makeText(context, msg, length).show();
    }

}
