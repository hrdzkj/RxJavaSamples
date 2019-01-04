package com.dagger2.dependencies;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by LiuYi on 2019/1/4.
 */
// @Component(modules = PresenterModule.class, dependencies = AppComponent.class)
@Subcomponent(modules = PresenterModule.class)
public interface PresenterComponent {
    void inject(UseDaggerActivity userDagger);
}
