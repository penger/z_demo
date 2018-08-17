package com.jike.testzkclient;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkConnection;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.OpResult;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

public class CreateNode2 {

	public static void main(String[] args) throws InterruptedException {
		
		ZkClient zc = new ZkClient("192.168.8.104:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok!");
		zc.subscribeChildChanges("/child", new IZkChildListener() {
			
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println(parentPath+"child changed");
				
			}
		});
//						zc.subscribeDataChanges("/data", new IZkDataListener() {
//							
//							@Override
//							public void handleDataDeleted(String dataPath) throws Exception {
//								
//							}
//							@Override
//							public void handleDataChange(String dataPath, Object data) throws Exception {
//								
//							}
//						});
//						zc.subscribeDataChanges("/data2", new IZkDataListener() {
//							
//							@Override
//							public void handleDataDeleted(String dataPath) throws Exception {
//								
//							}
//							@Override
//							public void handleDataChange(String dataPath, Object data) throws Exception {
//								
//							}
//						});
		Thread.sleep(Integer.MAX_VALUE);
					
	}
}
