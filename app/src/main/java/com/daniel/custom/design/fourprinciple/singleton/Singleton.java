package com.daniel.custom.design.fourprinciple.singleton;

/**
 * Created by daniel.xiao on 2016/10/20.
 * 不仅能够确保多线程安全，也能够保证单例对象的唯一性，同时也延迟了单例的实例化
 */
public class Singleton {

    private Singleton(){}

    private static class SingletonHolder{
        private static final Singleton sInstance = new Singleton();
    }

    public Singleton getInstance(){
        return SingletonHolder.sInstance;
    }
}
