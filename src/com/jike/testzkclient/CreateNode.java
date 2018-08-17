package com.jike.testzkclient;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkConnection;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.OpResult;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class CreateNode {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i=1;i<2 ;i++) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						ZkClient zc = new ZkClient("192.168.8.104:2181",10000,10000,new SerializableSerializer());
						System.out.println("conneted ok!");
						zc.subscribeChildChanges("/est", new IZkChildListener() {

							@Override
							public void handleChildChange(String parentPath, List<String> currentChilds)
									throws Exception {
								System.out.println("child changed"+parentPath);
								
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
