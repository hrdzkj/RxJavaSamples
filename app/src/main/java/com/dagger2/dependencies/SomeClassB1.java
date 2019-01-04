package com.dagger2.dependencies;

/**
 * Created by LiuYi on 2019/1/4.
 */
public class SomeClassB1 {
    private SomeClassA1 someClassA1;

    public SomeClassB1(SomeClassA1 someClassA1) {
        this.someClassA1 = someClassA1;
    }
}
