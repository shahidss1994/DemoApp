package com.shock.demoapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
    Toolbar toolbar;
    private ProgressDialog progressDialog;
    private DemoApp demoApp;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getApplicationContext());
        demoApp = DemoApp.get();
        context = demoApp.getApplicationContext();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    protected void setToolbar(String title) {
        try {
            toolbar.setTitle(title == null ? getResources().getString(R.string.app_name) : title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startActivity(Intent intent, final boolean finishActivity) {
        startActivity(intent);
        if (finishActivity)
            finish();
    }

    protected void showProgressDailog() {
        progressDialog.setMessage(getResources().getString(R.string.loading_please_wait_msg));
        progressDialog.show();
    }

    protected void hideProgressDailog() {
        progressDialog.hide();
    }

    protected void showToast(String msg,int length){
        Toast.makeText(context,msg,length).show();
    }

}
