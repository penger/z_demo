package com.base.designpattern.adapter;

/**
 * Created by gongp on 2018/8/23.
 */
public class Socket {

    public Volt getVolt(){
        return new Volt(120);
    }
}
