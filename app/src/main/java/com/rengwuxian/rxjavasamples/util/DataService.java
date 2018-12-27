package com.rengwuxian.rxjavasamples.util;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by LiuYi on 2018/12/25.
 */
public interface DataService {
    //指定get请求方式  指定路径 有时候路径除了baseUrl还有一部分比如 http://write.blog.csdn.net/mdeditor
    //http://write.blog.csdn.net/ 一般是baseUrl
    //而 mdeditor是相对路径的
    interface baiduString {
        @GET
        Call<String> baidu(@Url String url);
    }

    interface baiduResponse{
        @GET
        Call<ResponseBody> baidu(@Url String url);

    }


    interface baiduRxJava{

        @GET
        Observable<String> baidu(@Url String url);
    }
}
