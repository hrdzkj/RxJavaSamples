package com.rengwuxian.rxjavasamples;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.rxjavasamples.util.DataService;
import com.squareup.leakcanary.RefWatcher;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by LiuYi on 2018/12/27.
 */
public class MemoryReleaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_release);
        findViewById(R.id.textTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // retrofitString();
                // retrofitResponse();
                retrofitRxjava();
            }
        });
    }


    private void retrofitString()
    {

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl("http://www.baidu.com/")
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create())
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();
        //通过retrofit和定义的有网络访问方法的接口关联
        DataService.baiduString dataService = retrofit.create(DataService.baiduString.class);
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<String> baidu = dataService.baidu("http://wwww.baidu.com");
        //执行异步请求
        baidu.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MemoryReleaseActivity.this,  response.body(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void retrofitResponse()
    {

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl("http://www.baidu.com/")
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create())
                //定义client类型
                .client(new OkHttpClient())
                //创建
                .build();
        //通过retrofit和定义的有网络访问方法的接口关联
        DataService.baiduResponse dataService = retrofit.create(DataService.baiduResponse.class);
        //在这里又重新设定了一下baidu的地址，是因为Retrofit要求传入具体，如果是决定路径的话，路径会将baseUrl覆盖掉
        Call<ResponseBody> baidu = dataService.baidu("http://wwww.baidu.com");
        //执行异步请求
        baidu.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseString= response.body().string();
                    Log.v("liuyi",responseString);
                    Toast.makeText(MemoryReleaseActivity.this,  responseString, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);
    @SuppressLint("CheckResult")
    private void retrofitRxjava()
    {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //指定baseurl，这里有坑，最后后缀出带着“/”
                .baseUrl("http://www.baidu.com/")
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(new OkHttpClient())
                //创建
                .build();
        //通过retrofit和定义的有网络访问方法的接口关联
        DataService.baiduRxJava dataService = retrofit.create(DataService.baiduRxJava.class);
        dataService.baidu("http://wwww.baidu.com")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(provider.<String>bindToLifecycle())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        Toast.makeText(MemoryReleaseActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getInstance().getRefWatcher();
        refWatcher.watch(this);
    }
}

