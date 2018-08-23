package com.base.designpattern.adapter;

/**
 * Created by gongp on 2018/8/23.
 */
public class SocketObjectAdapterImpl implements SocketAdapter {


    private Socket socket;

    public SocketObjectAdapterImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Volt get120volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3volt() {
        Volt v = socket.getVolt();
        return convertVolt(v,40);
    }

    private Volt convertVolt(Volt v ,int i ){
        return  new Volt(v.getVolts()/i);
    }

}
