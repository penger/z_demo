package com.hadoop.rpc;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

public class RpcServer {

	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		Builder builder = new RPC.Builder(new Configuration());
		builder.setBindAddress("localhost");
		builder.setInstance(new AImpl());
		builder.setPort(808);
		builder.setProtocol(AProtocol.class);
		builder.setVerbose(true);
		Server server = builder.build();
		server.start();

	}

}
