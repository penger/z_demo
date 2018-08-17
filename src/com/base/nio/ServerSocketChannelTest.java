package com.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelTest {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		
		// listening for incoming connections
		while(true){
		    SocketChannel socketChannel =
		            serverSocketChannel.accept();

		    //do something with socketChannel...
		}

	}

}
