package com.shock.demoapp.ui.list.module;

import com.shock.demoapp.ui.list.interactor.ListInteractor;
import com.shock.demoapp.ui.list.interactor.ListInteractorImpl;
import com.shock.demoapp.ui.list.presenter.ListPresenter;
import com.shock.demoapp.ui.list.presenter.ListPresenterImpl;
import com.shock.demoapp.ui.list.view.ListView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shahid on 12/8/16.
 */
@Module
public class ListModule {

    public final ListView listView;

    public ListModule(ListView listView) {
        this.listView = listView;
    }

    @Provides
    ListView provideListView() {
        return listView;
    }

    @Provides
    ListInteractor provideListInteractor(ListInteractorImpl listInteractor) {
        return listInteractor;
    }

    @Provides
    ListPresenter provideListPresenter(ListPresenterImpl listPresenter) {
        return listPresenter;
    }

}
