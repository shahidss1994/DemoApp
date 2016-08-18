package com.shock.demoapp.ui.list.view;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.shock.demoapp.R;
import com.shock.demoapp.ui.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppBaseActivity {

    @BindView(R.id.root)
    View rootView;

    ListActivityFragment listActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        listActivityFragment = new ListActivityFragment();
        String tag = ListActivityFragment.class.getSimpleName();
        addFragment(R.id.container, listActivityFragment, tag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbar(getResources().getString(R.string.app_name), true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }
        removeFragment();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
