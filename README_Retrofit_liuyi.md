Retrofit 接
https://www.jianshu.com/p/0fda3132cf98
https://www.jianshu.com/p/45cb536be2f4o
https://blog.csdn.net/carson_ho/article/details/73732076

如何使用Retrofit请求非Restful AP
https://www.jianshu.com/p/2263242fa02d

demo
https://blog.csdn.net/chenjie0932/article/details/79558050


修改注解值
https://segmentfault.com/a/1190000011213222

restful api的理解
https://www.zhihu.com/question/28557115

如果你想异步的执行网络请求，并通过call.enqueue()处理请求结果
如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>


Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException.

https://www.jianshu.com/p/2e8b400909b7
 implementation 'com.squareup.okhttp3:logging-interceptor:3.8.1' //非必要依赖， log依赖，如果需要打印OkHttpLog需要添加
 
 
retrofit 可变参数：
https://blog.csdn.net/xieluoxixi/article/details/80092582