package com.jike.testzkclient;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;

import com.carrotsearch.hppc.IntIndexedContainer;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.source.tree.WhileLoopTree;

import kafka.utils.ZKStringSerializer$;

public class ListZK {
	
	public static ZkClient zc= null;

	public static void main(String[] args) {
		zc = new ZkClient("192.168.8.211:2181",20000,70000,ZKStringSerializer$.MODULE$);
		try {
			int i=0;
			while(true) {
				i++;
				Thread.sleep(1000);
				System.out.println("sleep "+ i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("conneted ok!");
		
		getZKchild("/");
	}

	
	
	public static void getZKchild(String path){
		int countChildren = zc.countChildren(path);
		if(countChildren==0) {
			Stat stat = new Stat();
			Object readData = zc.readData(path,stat);
			System.out.println(stat);
			System.out.println(path+":-->"+readData);
		}else {
			if(path.contains("jike") ) {
				
			}else{
//				if(path.contains("console-consumer-85238")) {
					System.out.println();
					System.out.println("path is :"+path);
//				}
				List<String> children = zc.getChildren(path);
				for (String c : children) {
					if(path.equals("/")) {
						path="";
					}
					String temp=path+"/"+c;
					getZKchild(temp);
				}
			}
			}
	}
}
