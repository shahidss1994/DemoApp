package com.shock.demoapp.ui.list.interactor;

import com.shock.demoapp.ui.list.module.DataItem;
import com.shock.demoapp.ui.list.presenter.OnCompletedListener;

import java.util.List;

/**
 * Created by shahid on 12/8/16.
 */
public interface ListInteractor {

    public void getData(final OnCompletedListener onCompletedListener);

    public void sortByPrice(List<DataItem> dataItems, final boolean asc);

    public void sortByStar(List<DataItem> dataItems, final boolean asc);

    public void sortByDiscount(List<DataItem> dataItems,final boolean asc);

}
