package com.dagger2.dependencies;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rengwuxian.rxjavasamples.R;

import javax.inject.Inject;

/**
 * Created by LiuYi on 2019/1/4.
 * https://blog.csdn.net/soslinken/article/details/70231089
 */
public class UseDaggerActivity extends Activity {
    @Inject
    SomeClassA1 classA1;

    @Inject
    SomeClassB1 classB1;

    //无法编译通过，因为SomeHideClassA1 在AppComponent 中没有显示的
      @Inject
      SomeHideClassA1 hideClassA1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_dagger);
        doInject();
    }

    private   void doInject() {
       /* dependencies方式
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();

        DaggerPresenterComponent.builder()
                .appComponent(appComponent)
                .presenterModule(new PresenterModule())
                .build()
                .inject(UseDaggerActivity.this);
                */

        // subComponent方式
        DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build().addSub(new PresenterModule()).inject(this);



        Log.v("---->","classA1="+(classA1==null?"null":"object"));
        Log.v("---->","classB1="+(classB1==null?"null":"object"));
        Log.v("---->","hideClassA1="+(hideClassA1==null?"null":"object"));
    }
}