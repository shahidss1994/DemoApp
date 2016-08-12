package com.shock.demoapp.ui.list.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shock.demoapp.R;
import com.shock.demoapp.ui.AppBaseActivity;

import butterknife.ButterKnife;

public class ListActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        setToolbar(getResources().getString(R.string.app_name));
    }
}
