package com.base.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannlTest {

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("localhost", 8080));
		
		//read from socketchannel
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		socketChannel.read(byteBuffer);
		
		//write to socketchannel
		
		byteBuffer.clear();
		while(byteBuffer.hasRemaining()) {
			socketChannel.write(byteBuffer);
		}
		
		//non-blocking mode
		socketChannel.configureBlocking(false);
		while (! socketChannel.finishConnect()) {
			// wait or do something else
		}
	}

}
