package com.shock.demoapp.ui.list.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shock.demoapp.R;
import com.shock.demoapp.di.DemoApp;
import com.shock.demoapp.ui.AppBaseFragment;
import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.module.ListModule;
import com.shock.demoapp.ui.list.presenter.ListPresenter;
import com.shock.demoapp.ui.list.view.dummy.DummyContent;
import com.shock.demoapp.ui.list.view.dummy.DummyContent.DummyItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListActivityFragment extends AppBaseFragment implements ListView, OnListFragmentInteractionListener {

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

    @Inject
    ListPresenter listPresenter;

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
        // Set the adapter
        /*if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerView.setAdapter(new ListItemRecyclerViewAdapter(context, DummyContent.ITEMS, this));*/
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListFragmentInteraction(DataItem item) {
        Toast.makeText(getContext(), item.getName(), Toast.LENGTH_SHORT).show();
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
    public void onSucess(List<DataItem> dataItems) {
        initializeView(dataItems);
    }

    @Override
    public void onFailure(String msg) {
        Log.d(TAG,msg);
    }

    private void initializeView(List<DataItem> dataItems){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ListItemRecyclerViewAdapter(context, dataItems, this));
    }

}
