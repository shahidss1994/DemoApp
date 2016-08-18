package com.shock.demoapp.ui.list.module;

import com.shock.demoapp.ui.list.interactor.ListInteractor;
import com.shock.demoapp.ui.list.interactor.ListInteractorImpl;
import com.shock.demoapp.ui.list.presenter.ListPresenter;
import com.shock.demoapp.ui.list.presenter.ListPresenterImpl;
import com.shock.demoapp.ui.list.presenter.OnCompletedListener;
import com.shock.demoapp.ui.list.view.ListActivityFragment;
import com.shock.demoapp.ui.list.view.ListView;
import com.shock.demoapp.ui.list.view.OnListFragmentInteractionListener;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shahid on 12/8/16.
 */
@Module
public class ListModule {

    public final ListActivityFragment listActivityFragment;

    public ListModule(ListActivityFragment listActivityFragment) {
        this.listActivityFragment = listActivityFragment;
    }

    @Provides
    ListView provideListView() {
        return listActivityFragment;
    }

    @Provides
    ListInteractor provideListInteractor(ListInteractorImpl listInteractor) {
        return listInteractor;
    }

    @Provides
    ListPresenter provideListPresenter(ListPresenterImpl listPresenter) {
        return listPresenter;
    }

    @Provides
    OnListFragmentInteractionListener provideListener() {
        return (OnListFragmentInteractionListener) listActivityFragment;
    }

}
