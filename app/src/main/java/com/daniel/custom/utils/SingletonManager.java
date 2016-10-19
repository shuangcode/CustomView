package com.daniel.custom.utils;

import java.util.HashMap;

/**
 * Created by daniel.xiao on 2016/10/20.
 * 单例管理类 : 可以通过统一的接口进行单例的获取操作
 */
public class SingletonManager {

    private static HashMap<String, Object> objMap = new HashMap<>();

    private SingletonManager(){}

    public static void registerService(String key, Object instance){
        // 查看实例是否已经存在
        if(!objMap.containsKey(key)){
            objMap.put(key, instance);
        }
    }

    public static Object getService(String key){
        return objMap.get(key);
    }
}
