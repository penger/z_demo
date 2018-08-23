package com.base.designpattern.adapter;

/**
 * Created by gongp on 2018/8/23.
 */
public class TestAdapter {
    public static void main(String[] args) {
//        InputStreamReader inputStreamReader = new InputStreamReader();
        testClassAdapter();
        testObjectAdapter();
    }

    private static void testClassAdapter() {
        SocketAdapter adapter = new SocketClassAdapter(new Socket());
        System.out.println(adapter.get120volt().getVolts());
        System.out.println(adapter.get12volt().getVolts());
        System.out.println(adapter.get3volt().getVolts());
    }

    private static void testObjectAdapter() {
        SocketAdapter adapter = new SocketObjectAdapterImpl(new Socket());
        System.out.println(adapter.get3volt().getVolts());
        System.out.println(adapter.get12volt().getVolts());
        System.out.println(adapter.get120volt().getVolts());
    }
}
