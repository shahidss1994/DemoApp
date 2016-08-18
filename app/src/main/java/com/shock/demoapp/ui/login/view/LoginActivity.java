package com.shock.demoapp.ui.login.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.shock.demoapp.R;
import com.shock.demoapp.ui.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppBaseActivity {

    private boolean backPressed = false;

    @BindView(R.id.root)
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbar(getResources().getString(R.string.app_name), false);
    }

    @Override
    public void onBackPressed() {
        if(backPressed){
            finish();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        },2100);
        Snackbar.make(rootView,getResources().getString(R.string.back_pressed),Snackbar.LENGTH_LONG).show();
        backPressed = true;
    }
}
