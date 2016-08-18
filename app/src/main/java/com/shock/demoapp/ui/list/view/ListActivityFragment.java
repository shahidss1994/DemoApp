package com.shock.demoapp.ui.list.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;

import com.shock.demoapp.R;
import com.shock.demoapp.di.DemoApp;
import com.shock.demoapp.ui.AppBaseFragment;
import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.module.ListModule;
import com.shock.demoapp.ui.list.presenter.ListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListActivityFragment extends AppBaseFragment implements ListView,
        OnListFragmentInteractionListener, View.OnClickListener {

    private final String TAG = ListActivityFragment.class.getSimpleName();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private Context context;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.tv_data_not_found)
    AppCompatTextView tvDataNotFound;

    @BindView(R.id.btn_retry)
    AppCompatButton btnRetry;

    @BindView(R.id.rl_sort)
    RelativeLayout rlSort;

    @BindView(R.id.ll_sort)
    LinearLayoutCompat llSort;

    @BindView(R.id.ctv_price_low_to_high)
    CheckedTextView ctvPriceLowToHigh;

    @BindView(R.id.ctv_price_high_to_low)
    CheckedTextView ctvPriceHighToLow;

    @BindView(R.id.ctv_star_low_to_high)
    CheckedTextView ctvStarLowToHigh;

    @BindView(R.id.ctv_star_high_to_low)
    CheckedTextView ctvStarHighToLow;

    @BindView(R.id.ctv_dis_low_to_high)
    CheckedTextView ctvDisLowToHigh;

    @BindView(R.id.ctv_dis_high_to_low)
    CheckedTextView ctvDisHighToLow;

    @Inject
    ListPresenter listPresenter;

    public CheckedTextView previousCheckedTextView;

    public ListActivityFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ListActivityFragment newInstance(int columnCount) {
        ListActivityFragment fragment = new ListActivityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        DemoApp.get().getAppComponent().plus(new ListModule(this)).inject(this);
        listPresenter.getData();
        ctvPriceHighToLow.setOnClickListener(this);
        ctvPriceLowToHigh.setOnClickListener(this);
        ctvStarLowToHigh.setOnClickListener(this);
        ctvStarHighToLow.setOnClickListener(this);
        ctvDisHighToLow.setOnClickListener(this);
        ctvDisLowToHigh.setOnClickListener(this);
        rlSort.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                toggleSortView();
                return false;
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_filter:
                if (recyclerView.getAdapter() != null) {
                    toggleSortView();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListFragmentInteraction(DataItem item) {
        showInDetail(item);
    }

    @Override
    public void showProgress() {
        showProgressDailog();
    }

    @Override
    public void hideProgress() {
        hideProgressDailog();
    }

    @Override
    public void onSuccess(ListItemRecyclerViewAdapter adapter) {
        hideProgress();
        recyclerView.setVisibility(View.VISIBLE);
        tvDataNotFound.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String msg) {
        recyclerView.setVisibility(View.GONE);
        tvDataNotFound.setVisibility(View.VISIBLE);
        btnRetry.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_retry)
    public void onClickBtnRetry() {
        listPresenter.getData();
    }

    private void showInDetail(DataItem dataItem) {
        ListActivityDetailFragment listActivityDetailFragment = new ListActivityDetailFragment();
        String tag = ListActivityFragment.class.getSimpleName();
        Bundle bundle = new Bundle();
        bundle.putSerializable(getResources().getString(R.string.serialize_data), dataItem);
        listActivityDetailFragment.setArguments(bundle);
        replaceFragment(R.id.container, listActivityDetailFragment, tag);
    }


    @Override
    public void onClick(View view) {
        if (previousCheckedTextView != null)
            previousCheckedTextView.setChecked(false);
        int id = view.getId();
        switch (id) {
            case R.id.ctv_price_high_to_low:
                ctvPriceHighToLow.setChecked(true);
                toggleSortView();
                listPresenter.sortByPrice(false);
                break;
            case R.id.ctv_price_low_to_high:
                ctvPriceLowToHigh.setChecked(true);
                toggleSortView();
                listPresenter.sortByPrice(true);
                break;
            case R.id.ctv_star_high_to_low:
                ctvStarHighToLow.setChecked(true);
                toggleSortView();
                listPresenter.sortByStar(false);
                break;
            case R.id.ctv_star_low_to_high:
                ctvStarLowToHigh.setChecked(true);
                toggleSortView();
                listPresenter.sortByStar(true);
                break;
            case R.id.ctv_dis_high_to_low:
                ctvDisHighToLow.setChecked(true);
                toggleSortView();
                listPresenter.sortByDiscount(false);
                break;
            case R.id.ctv_dis_low_to_high:
                ctvDisLowToHigh.setChecked(true);
                toggleSortView();
                listPresenter.sortByDiscount(true);
                break;
        }
        previousCheckedTextView = (CheckedTextView) view;
    }

    public void toggleSortView() {
        Animation animation = AnimationUtils.loadAnimation(context,
                rlSort.getVisibility() == View.VISIBLE ? R.anim.slide_down : R.anim.slide_up);
        llSort.startAnimation(animation);
        if (rlSort.getVisibility() == View.VISIBLE) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rlSort.setVisibility(View.GONE);
                    llSort.setVisibility(View.GONE);
                    rlSort.setFocusable(false);
                }
            }, animation.getDuration());
        } else {
            rlSort.setVisibility(View.VISIBLE);
            llSort.setVisibility(View.VISIBLE);
            rlSort.setFocusable(true);
        }
    }

}
