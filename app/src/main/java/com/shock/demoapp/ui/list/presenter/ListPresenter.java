package com.shock.demoapp.ui.list.presenter;

import com.shock.demoapp.ui.list.module.DataItem;

import java.util.List;

/**
 * Created by shahid on 12/8/16.
 */
public interface ListPresenter extends OnCompletedListener {

    public void getData();

    public void sortByPrice(boolean asc);

    public void sortByStar(final boolean asc);

    public void sortByDiscount(final boolean asc);

}
