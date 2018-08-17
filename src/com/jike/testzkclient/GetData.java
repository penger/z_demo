package com.jike.testzkclient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import com.sun.org.apache.xml.internal.security.Init;
import com.sun.xml.bind.v2.model.annotation.RuntimeInlineAnnotationReader;

public class GetData {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i=1;i<2 ;i++) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						ZkClient zc = new ZkClient("192.168.8.211:2181",10000,10000,new SerializableSerializer());
						System.out.println("conneted ok!");
						zc.subscribeDataChanges("/itstyle", new IZkDataListener() {
							@Override
							public void handleDataDeleted(String dataPath) throws Exception {
								System.out.println(dataPath+"delete");
								
							}
							@Override
							public void handleDataChange(String dataPath, Object data) throws Exception {
								System.out.println(dataPath+"changed");
								
							}
						});
						Thread.sleep(Integer.MAX_VALUE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(10000000000l, TimeUnit.DAYS);
	}
	
}
