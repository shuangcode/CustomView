package com.daniel.custom.bean;

import java.io.Serializable;

/**
 * Created by daniel.xiao on 2016/10/19.
 */
public class Person implements Serializable{

    private static final long serialVersionUID = -5921229352222636376L;

    public String name;
    public int age;
    public String sex;
    public boolean isSingle;

    @Override
    public String toString() {
        return "name = " + name +" age = " + age +" sex = " + sex + "isSingle" + isSingle;
     }
}
