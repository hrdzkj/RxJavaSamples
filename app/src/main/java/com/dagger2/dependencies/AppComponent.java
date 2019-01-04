package com.dagger2.dependencies;

import dagger.Component;

/**
 * Created by LiuYi on 2019/1/4.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    //将AppModule中的SomeClassA1暴露出来，以便于其他依赖于AppComponent的Component调用
    //SomeClassA1 someClassA1();

    //需要将SubComponent 追加到 被依赖的Component中
    PresenterComponent addSub(PresenterModule PresenterModule);
}
