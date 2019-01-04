package com.dagger2.dependencies;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuYi on 2019/1/4.
 */
@Module
public class AppModule {
    @Provides
    public SomeClassA1 provideSomeClassA1() {
        return new SomeClassA1();
    }

    @Provides
    public SomeHideClassA1 provideSomeHideClassA1() {
        return new SomeHideClassA1();
    }
}
