package com.jike.mastersel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

public class WorkServer {

	private volatile boolean running = false;

	private ZkClient zkClient;
	
	//竞争创建master节点，当创建成功，那么获取master，获取后5秒钟删除当前/master节点，
	//所有server订阅 /master事件，当发现节点出现变化后，抢占master的创建
	private static final String MASTER_PATH = "/master";

	private IZkDataListener dataListener;

	private RunningData serverData;

	private RunningData masterData;
	
	private ScheduledExecutorService delayExector = Executors.newScheduledThreadPool(1);
	private int delayTime = 5;

	public WorkServer(RunningData rd) {
		this.serverData = rd;
		this.dataListener = new IZkDataListener() {

			public void handleDataDeleted(String dataPath) throws Exception {
				// TODO Auto-generated method stub
				
				//takeMaster();
				
				
				if (masterData!=null && masterData.getName().equals(serverData.getName())){
					takeMaster();
					
				}else{
					//五秒后重新获取master的权限
					delayExector.schedule(new Runnable(){
						public void run(){
							takeMaster();
						}
					}, delayTime, TimeUnit.SECONDS);
					
				}
				
				
			}

			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				// TODO Auto-generated method stub

			}
		};
	}

	public ZkClient getZkClient() {
		return zkClient;
	}

	public void setZkClient(ZkClient zkClient) {
		this.zkClient = zkClient;
	}

	public void start() throws Exception {
		if (running) {
			throw new Exception("server has startup...");
		}
		running = true;
		zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
		takeMaster();

	}

	public void stop() throws Exception {
		if (!running) {
			throw new Exception("server has stoped");
		}
		running = false;
		
		delayExector.shutdown();

		zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);

		releaseMaster();

	}

	private void takeMaster() {
		//如果非运行状态那么退出
		if (!running) {
			System.out.println(serverData.getName()+ "退出节点竞争");
			return;
		}

		try {
			System.out.println(serverData.getName()+ "尝试创建临时节点");
			//在master节点创建 用户的数据的临时节点
			zkClient.create(MASTER_PATH, serverData, CreateMode.EPHEMERAL);
			//给用户数据赋值给serverdata
			masterData = serverData;
			System.out.println(serverData.getName()+" is master");
			delayExector.schedule(new Runnable() {			
				public void run() {
					// 延迟五秒钟，判断当前是不是主节点，如果不是主节点，那么释放master控制
					if (checkMaster()){
						releaseMaster();
						//退出竞争
						running=false;
					}
				}
			}, 5, TimeUnit.SECONDS);
			
		} catch (ZkNodeExistsException e) {
			RunningData runningData = zkClient.readData(MASTER_PATH, true);
			System.out.println(serverData.getName()+ "竞争失败，当前主节点值为"+ runningData.getName());
			if (runningData == null) {
				takeMaster();
			} else {
				masterData = runningData;
			}
		} catch (Exception e) {
			// ignore;
		}

	}

	private void releaseMaster() {
		if (checkMaster()) {
			System.out.println("删除 当前 /master 值为："+serverData.getName());
			zkClient.delete(MASTER_PATH);

		}

	}

	private boolean checkMaster() {
		try {
			RunningData eventData = zkClient.readData(MASTER_PATH);
			masterData = eventData;
			if (masterData.getName().equals(serverData.getName())) {
				return true;
			}
			return false;
		} catch (ZkNoNodeException e) {
			return false;
		} catch (ZkInterruptedException e) {
			return checkMaster();
		} catch (ZkException e) {
			return false;
		}
	}

}
