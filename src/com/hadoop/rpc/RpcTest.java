package com.hadoop.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RpcTest {

	public static void main(String[] args) throws IOException {
		AProtocol proxy = RPC.getProxy(AProtocol.class, 121212L, new InetSocketAddress("localhost", 808), new Configuration());
		String date = proxy.getDate("gongpeng");
		System.out.println(date);
		RPC.stopProxy(proxy);
	}

}
