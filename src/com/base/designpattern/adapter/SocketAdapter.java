package com.base.designpattern.adapter;

/**
 * Created by gongp on 2018/8/23.
 */
public interface SocketAdapter {
    public Volt get120volt();
    public Volt get12volt();
    public Volt get3volt();
}
