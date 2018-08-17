package com.base.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class NIOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void copyfile() throws IOException {
		RandomAccessFile file = new RandomAccessFile("d:\\A.java", "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
		int bytesread = channel.read(buffer);
		while (bytesread!=-1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.println("--------------");
				System.out.print((char)buffer.get());
			}
			buffer.compact();
			bytesread=channel.read(buffer);
		}
		
	}
	
	public void ScatterAndGather() throws IOException {
		
		 RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		 FileChannel inChannel = aFile.getChannel();
		 ByteBuffer header = ByteBuffer.allocate(1024);
		 ByteBuffer body = ByteBuffer.allocate(1024);
		 ByteBuffer[] array = {header,body};
		 //scatter read
		 inChannel.read(array);
		 inChannel.write(array);
		
	}
	
	@Test
	public void testTransformBetweenChannl() throws IOException {
		
		RandomAccessFile fromFile = new RandomAccessFile("d://fromFile.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("d://toFile.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count    = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
	}
	
	
	public void testRegisterChannels2Selector() throws IOException {
		SocketChannel channel = null;
		Selector selector = Selector.open();
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_ACCEPT|SelectionKey.OP_READ);
	}
	
	public void testSelector() throws IOException {
		Selector selector = Selector.open();
		SocketChannel channel = null;
		channel.configureBlocking(false);

		SelectionKey keyx = channel.register(selector, SelectionKey.OP_READ);


		while(true) {

		  int readyChannels = selector.select();

		  if(readyChannels == 0) continue;


		  Set<SelectionKey> selectedKeys = selector.selectedKeys();

		  Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

		  while(keyIterator.hasNext()) {

		    SelectionKey key = keyIterator.next();

		    if(key.isAcceptable()) {
		        // a connection was accepted by a ServerSocketChannel.

		    } else if (key.isConnectable()) {
		        // a connection was established with a remote server.

		    } else if (key.isReadable()) {
		        // a channel is ready for reading

		    } else if (key.isWritable()) {
		        // a channel is ready for writing
		    }

		    keyIterator.remove();
		  }
		}
	}
	
	
	
	
	
	
}
