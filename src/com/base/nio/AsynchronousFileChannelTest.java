package com.base.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class AsynchronousFileChannelTest {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("data/test.xml");
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		AsynchronousFileChannel fileChannel =
		    AsynchronousFileChannel.open(path, StandardOpenOption.READ);

		Future<Integer> operation = fileChannel.read(buffer, 0);
		while(! operation.isDone()) {
			
		}
		buffer.flip();
		byte[] data = new byte[buffer.limit()];
		buffer.get(data);
		System.out.println(new String(data));
		buffer.clear();
		
		
	}

}
