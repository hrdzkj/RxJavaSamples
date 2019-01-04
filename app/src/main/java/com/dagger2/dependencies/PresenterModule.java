package com.dagger2.dependencies;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuYi on 2019/1/4.
 */
@Module
public class PresenterModule {
    @Provides
    public SomeClassB1 provideSomeClassB1(SomeClassA1 someClassA1) {
        // SomeClassB1 依赖 SomeClassA1
        return new SomeClassB1(someClassA1);
    }
}

