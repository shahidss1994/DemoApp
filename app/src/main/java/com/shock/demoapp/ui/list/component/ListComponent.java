package com.shock.demoapp.ui.list.component;

import com.shock.demoapp.ui.list.module.ListModule;
import com.shock.demoapp.ui.list.view.ListActivityFragment;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by shahid on 12/8/16.
 */
@Subcomponent(modules = {ListModule.class})
public interface ListComponent {

    void inject(ListActivityFragment listActivityFragment);

}
