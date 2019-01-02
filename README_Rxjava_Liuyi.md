rxjava是可观测序列的异步的，基于事件的程序库。
利用图来写代码
****************************************
除了 Observer 接口之外，RxJava 还内置了一个实现了 Observer 的抽象类Subscriber。 Subscriber 对 Observer 接口进行了一些扩展.

**********************************
Action1:subscribe() 还支持不完整定义的回调（如果只关系收到事件），RxJava 会自动根据定义创建出 Subscriber.（同理 Func1，Consumer）如下：
Action1<String> onNextAction = new Action1<String>() {
    // onNext()
    @Override
    public void call(String s) {
        Log.d(tag, s);
    }
};
Action1<Throwable> onErrorAction = new Action1<Throwable>() {
    // onError()
    @Override
    public void call(Throwable throwable) {
        // Error handling
    }
};
Action0 onCompletedAction = new Action0() {
    // onCompleted()
    @Override
    public void call() {
        Log.d(tag, "completed");
    }
};

// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
*********************************


Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。
Observer 即观察者，它决定事件触发的时候将有怎样的行为

create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建可观察者的方法：just,from(T[])/from(Iterable<? extends T>)

*******************************
在 RxJava 的默认规则中，事件的发出和消费都是在同一个线程的。
在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：
在哪个线程调用subscribe()发起订阅，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。

subscribeOn(): 指定 subscribe() 所发生的线程。
observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
不同于 observeOn() ， subscribeOn() 的位置放在哪里都可以，但它是只能调用一次的。
*******************************

retrywhen操作符的用法：https://blog.csdn.net/samuel__liu/article/details/78691664
主动抛出错误：return Observable.just(response);
考虑情况：
有时候参数可变。   


Android Lifecycle  
LifecycleObserver接口（ Lifecycled的观察者） ---观察者，当生命周期方法调用时会收到通知 
LifecycleOwner接口（Lifecycle持有者）  ---Fragment/Activity
Lifecycle(生命周期)  
State(当前生命周期所处状态)  
Event(当前生命周期改变对应的事件)
使用场景 https://www.cnblogs.com/zqlxtt/p/6887938.html 总结
1）减少在activity/fragment中写生命周期方法,保持activity/fragment的简洁
2）LiveData和ViewModel
LiveData 设计的目的是能够保证数据和UI统一。当LiveData对象保存的数据发生变化时，onChanged()方法可以进行相应的处理。
ViewModel 设计的目的是解决activity重建数据不易保存，急fragmeng/activity间数据共享的问题。 ViewModelProviders.of(getActivity()).get(MyViewModel .class);  
ViewModel 有两个功能, 第一个功能可以使 ViewModel 以及 ViewModel 中的数据在屏幕旋转或配置更改引起的 Activity 重建时存活下来, 重建后数据可继续使用,
第二个功能可以帮助开发者轻易实现 Fragment 与 Fragment 之间, Activity 与 Fragment 之间的通讯以及共享数据
M-------------V------------VM
Model         View         ViewModel处理业务逻辑以及获取数据


https://www.jianshu.com/p/24af4c102f62
dagger2
负责依赖对象的创建。使用依赖注入的方式构建对象，模块间解耦。

********************************
rxjava内存泄漏问题
autoDispose---注入了as操作符
rxLifeCycle--需要从RxActivity集成
都不是解决内存泄漏的好办法


*****************  
MVVM的demo请看https://github.com/googlesamples/android-architecture-components.git BasicSample项目的ProductFragment.java  
问题：1) 网络异常传递null 能否处理? 
需要翻墙 https://developer.android.com/jetpack/docs/guide#addendum     
2)Rxjava Disposed放在ViewModel生命周期？  
直到当前Activity被系统销毁时，Framework会调用ViewModel的onCleared()方法，我们可以在onCleared()方法中做一些资源清理操作。  
3）https://www.jianshu.com/p/02beaf0507ab  一些注意的问题  
标注使用retrofit方式
public interface Webservice {  
    @GET("/users/{user}")  
    Call<User> getUser(@Path("user") String userId);  
}  



