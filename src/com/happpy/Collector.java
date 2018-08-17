package com.happpy;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

//http://node1:50070/jmx?qry=Hadoop:service=NameNode,name=NameNodeInfo
//http://node1:50070/jmx?qry=Hadoop:service=NameNode,name=NameNodeStatus
//http://node1:50070/jmx?qry=Hadoop:service=NameNode,name=FSNamesystemState
//http://node1:50070/jmx?qry=java.lang:type=Memory

//功能
//1.打印namenode的active状态
//2.打印dfs的使用量
//3.打印datanode的基本信息

//

public class Collector {

	private static String hdfs_url = "";
	private static String[] hdfs_urls = new String[] { "/jmx?qry=Hadoop:service=NameNode,name=NameNodeInfo",
			"/jmx?qry=Hadoop:service=NameNode,name=FSNamesystemState", "/jmx?qry=java.lang:type=Memory" };
	
	
	private static String[] hbase_url = new String[] { "/master-status", "/jmx?get=Hadoop:service=HBase,name=Master,sub=Server::tag.isActiveMaster", "" };

	private static HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

	public static void main(String[] args) throws IOException {

//		getHDFSInfo("http://node1:50070", "http://node2:50070");
		 getHbaseInfo("http://node1:60010", "http://node2:60010");
		// getZKInfo();
	}

	private static void getZKInfo() {
		// TODO Auto-generated method stub

	}

	private static void getHbaseInfo(String ...hms) {
		
		String activeHmaster = getActiveHmaster(hms);
		
		
		Page HbaseInfohtml = httpClientDownloader.download(new Request(activeHmaster+hbase_url[0]), Site.me().setCharset(null).toTask());
		Html html = HbaseInfohtml.getHtml();
		List<String> all = html.xpath("//table[@class=table and @class=table-striped]").all();
		for (int t = 0; t < all.size() - 1; t++) {
			Html heapSizeTable = new Html(all.get(t));
			List<String> title = heapSizeTable.xpath("//th/text()").all();
			for (int i = 0; i < title.size(); i++) {
				String heapTitle = title.get(i);
				List<String> infos = null;
				if (i == 0) {
					infos = heapSizeTable.xpath("//tr/td[1]/a/text()").all();
				} else {
					infos = heapSizeTable.xpath("//tr/td[" + (i + 1) + "]/text()").all();
				}
				for (String info : infos) {
					System.out.println(info);
				}
				
			}
		}
		

	}

	private static String getActiveHmaster(String[] hms) {
		String activeHmaster = null;
		for (String hm : hms) {
			String url = hm+hbase_url[1];
			String is_active = new Json(httpClientDownloader
					.download(new Request(url), Site.me().setCharset(null).toTask()).getRawText().replace("tag.isActiveMaster", "isActiveMaster"))
							.jsonPath("beans[0].isActiveMaster").get();
			if(is_active.equals("true")) {
				if(activeHmaster ==null) {
					activeHmaster=hm;
				}else {
					throw new RuntimeException("多个active节点");
				}
			}
		}
		if(activeHmaster==null) throw new RuntimeException("未发现存活active hmaster");
		System.out.println("活跃的hmaster地址为："+activeHmaster);
		return activeHmaster;
	}

	private static void getHDFSInfo(String nn1, String nn2) {

		String activeNameNode = getActiveNameNode(nn1, nn2);

		Page NameNodeInfohtml = httpClientDownloader.download(new Request(activeNameNode + hdfs_urls[0]),
				Site.me().setCharset(null).toTask());
		get_JMX_NameNodeInfo(NameNodeInfohtml.getRawText());

		Page FSNamesystemStatehtml = httpClientDownloader.download(new Request(activeNameNode + hdfs_urls[1]),
				Site.me().setCharset(null).toTask());
		get_JMX_FSNamesystemState(FSNamesystemStatehtml.getRawText());

		// NameNode JVM值
		Page Memoryhtml = httpClientDownloader.download(new Request(nn1 + hdfs_urls[2]),
				Site.me().setCharset(null).toTask());
		get_JMX_Memory(Memoryhtml.getRawText());

		Page Memoryhtml2 = httpClientDownloader.download(new Request(nn2 + hdfs_urls[2]),
				Site.me().setCharset(null).toTask());
		get_JMX_Memory(Memoryhtml.getRawText());
	}

	private static String getActiveNameNode(String nn1, String nn2) {
		
		String activeNameNode="";
		String sub_fix = "/jmx?get=Hadoop:service=NameNode,name=NameNodeStatus::State";
		String is_active = new Json(httpClientDownloader
				.download(new Request(nn1 + sub_fix), Site.me().setCharset(null).toTask()).getRawText())
						.jsonPath("beans[0].State").get();
		String is_active2 = new Json(httpClientDownloader
				.download(new Request(nn2 + sub_fix), Site.me().setCharset(null).toTask()).getRawText())
						.jsonPath("beans[0].State").get();
		if (is_active.equalsIgnoreCase("active") && is_active2.equalsIgnoreCase("standby")) {
			activeNameNode=nn1;
			System.out.println("active is :"+nn1+ "          standby is :"+nn2);
		}else if(is_active.equalsIgnoreCase("standby") && is_active2.equalsIgnoreCase("active")){
			System.out.println("active is :"+nn2+ "          standby is :"+nn1);
			activeNameNode=nn2;
		}else {
			throw new RuntimeException("无法找到主NameNode:集群异常");
		}
		return activeNameNode;
	}

	private static void get_JMX_Memory(String memory) {
		// System.out.println(memory);
		Json json = new Json(memory);
		
		String jvm="committed :%s init: %s max: %s used: %s";
		System.out.format(jvm, size(json.jsonPath("beans[0].HeapMemoryUsage.committed").get()),
				size(json.jsonPath("beans[0].HeapMemoryUsage.init").get()),
				size(json.jsonPath("beans[0].HeapMemoryUsage.max").get()),
				size(json.jsonPath("beans[0].HeapMemoryUsage.used").get()));
		System.out.println();
	}

	private static void get_JMX_FSNamesystemState(String fsNamesystemState) {
		Json json = new Json(fsNamesystemState);
		String blocksTotal= json.jsonPath("beans[0].BlocksTotal").get();
		String capacityTotal=size(json.jsonPath("beans[0].CapacityTotal").get());
		String capacityUsed=size(json.jsonPath("beans[0].CapacityUsed").get());
		String capacityRemaining=size(json.jsonPath("beans[0].CapacityRemaining").get());
		String blockDeletionStartTime=json.jsonPath("beans[0].BlockDeletionStartTime").get();
		String numLiveDataNodes=json.jsonPath("beans[0].NumLiveDataNodes").get();
		String numDeadDataNodes=json.jsonPath("beans[0].NumDeadDataNodes").get();

		System.out.format("当前块总数：%s ,配置容量：%s , 已经使用的配置容量：%s , 未使用的配置容量 ：%s, 块删除开始时间：%s ,存活的DataNode 数量：%s, 死亡DataNode数量：%s", blocksTotal,capacityTotal,
				capacityUsed,capacityRemaining,blockDeletionStartTime,numLiveDataNodes,numDeadDataNodes);
		System.out.println();
	}

	public static void get_JMX_NameNodeInfo(String nameNodeInfo) {

		Json json = new Json(nameNodeInfo);

		// NameNode概况
		String totalBlocks = json.jsonPath("beans[0].TotalBlocks").get();
		String used = size(json.jsonPath("beans[0].Used").get());
		String free = size(json.jsonPath("beans[0].Free").get());
		String safemode = json.jsonPath("beans[0].Safemode").get();
		String nonDfsUsedSpace = size(json.jsonPath("beans[0].NonDfsUsedSpace").get());
		String percentUsed = json.jsonPath("beans[0].PercentUsed").get();
		String blockPoolUsedSpace =size( json.jsonPath("beans[0].BlockPoolUsedSpace").get());
		String percentBlockPoolUsed = size(json.jsonPath("beans[0].PercentBlockPoolUsed").get());
		String totalFiles = json.jsonPath("beans[0].TotalFiles").get();
		String numberOfMissingBlocks = json.jsonPath("beans[0].NumberOfMissingBlocks").get();
		String deadNodes = json.jsonPath("beans[0].DeadNodes").get();

		String percentRemaining = json.jsonPath("beans[0].PercentRemaining").get();
		String outline="块总数 : %s 使用量 : %s 空闲量 : %s 安全模式 : %s 非DFS占用空间 : %s 空间使用率 : %s 块池量 : %s 块池使用率 : %s 文件总数 : %s 丢失块总数 : %s 死亡节点数 : %s 剩余容量：%s";
		System.out.format(outline, totalBlocks,used,free,safemode,nonDfsUsedSpace,percentUsed,blockPoolUsedSpace,percentBlockPoolUsed,totalFiles,numberOfMissingBlocks,deadNodes,percentRemaining);
		System.out.println();
		
		
		
		// DataNode 概况
		String liveNodes = json.jsonPath("beans[0].LiveNodes").get();

		// {"node4":{"infoAddr":"192.168.8.104:50075","infoSecureAddr":"192.168.8.104:0","xferaddr":"192.168.8.104:50010","lastContact":0,"usedSpace":999010304,"adminState":"In
		// Service","nonDfsUsedSpace":6355480576,"capacity":40465752064,"numBlocks":50,"version":"2.6.0-cdh5.5.0","used":999010304,"remaining":33111261184,"blockScheduled":0,"blockPoolUsed":999010304,"blockPoolUsedPercent":2.4687798,"volfails":0},"node5":{"infoAddr":"192.168.8.105:50075","infoSecureAddr":"192.168.8.105:0","xferaddr":"192.168.8.105:50010","lastContact":2,"usedSpace":999075840,"adminState":"In
		// Service","nonDfsUsedSpace":6317801472,"capacity":40465752064,"numBlocks":57,"version":"2.6.0-cdh5.5.0","used":999075840,"remaining":33148874752,"blockScheduled":0,"blockPoolUsed":999075840,"blockPoolUsedPercent":2.4689417,"volfails":0},"node3":{"infoAddr":"192.168.8.103:50075","infoSecureAddr":"192.168.8.103:0","xferaddr":"192.168.8.103:50010","lastContact":0,"usedSpace":998993920,"adminState":"In
		// Service","nonDfsUsedSpace":6425006080,"capacity":40465752064,"numBlocks":48,"version":"2.6.0-cdh5.5.0","used":998993920,"remaining":33041752064,"blockScheduled":0,"blockPoolUsed":998993920,"blockPoolUsedPercent":2.4687393,"volfails":0}}
		// 正则获取 datanode值并解析内部结果
		Json liveNodesJson = new Json(liveNodes);
		Pattern pattern = Pattern.compile("([a-zA-Z_0-9]+)\":\\{");
		Matcher matcher = pattern.matcher(liveNodes);
		String str="infoAddr: %s  lastContact: %s   usedSpace: %s   adminState: %s   nonDfsUsedSpace: %s   capacity: %s   numBlocks: %s   used: %s   remaining: %s   blockPoolUsed: %s   blockPoolUsedPercent: %s ";
		while (matcher.find()) {
			String nodeName = matcher.group(1);
			System.out.println(nodeName + "详细信息：");
		
			String datanode_jmx=liveNodesJson.jsonPath(nodeName + ".infoAddr").get();
			datanode_jmx="http://"+datanode_jmx+"/jmx?qry=java.lang:type=Memory";
			
			Page datanodePage = httpClientDownloader.download(new Request(datanode_jmx),
					Site.me().setCharset(null).toTask());
			get_JMX_Memory(datanodePage.getRawText());
			
			
			System.out.format(str,
					liveNodesJson.jsonPath(nodeName + ".infoAddr").get(),
					liveNodesJson.jsonPath(nodeName + ".lastContact").get(),
			size(liveNodesJson.jsonPath(nodeName + ".usedSpace").get()),
			liveNodesJson.jsonPath(nodeName + ".adminState").get(),
			size(liveNodesJson.jsonPath(nodeName + ".nonDfsUsedSpace").get()),
			size(liveNodesJson.jsonPath(nodeName + ".capacity").get()),
			liveNodesJson.jsonPath(nodeName + ".numBlocks").get(),
			size(liveNodesJson.jsonPath(nodeName + ".used").get()),
			size(liveNodesJson.jsonPath(nodeName + ".remaining").get()),
			size(liveNodesJson.jsonPath(nodeName + ".blockPoolUsed").get()),
			liveNodesJson.jsonPath(nodeName + ".blockPoolUsedPercent").get());
			System.out.println();
		}

	}
	
	
	
	


	public static void formatPrint(String key, Object value, boolean resize) {
		if (resize) {
			System.out.format("%s ------> %s\n", key, size(value.toString()));
		} else {
			System.out.format("%s ------> %s\n", key, value);
		}
	}

	public static void formatPrint(String key, Object value) {
		formatPrint(key, value, false);

	}

	public static String size(String size) {
		float f = Float.parseFloat(size);
		float k = 1024;
		float m = k * 1024;
		float g = m * 1024;
		float t = g * 1024;
		float p = t * 1024;
		
		DecimalFormat d = new DecimalFormat("0.00");

		if (f < k) {
			return (f + "B");
		} else {
			if (f < m) {
				return (d.format(f / k) + "KB");
			} else {
				if (f < g) {
					return (d.format(f / m) + "MB");
				} else {
					if (f < t) {
						return (d.format(f / g) + "GB");
					} else {
						if (f < p) {
							return (d.format(f / t) + "TB");
						} else {
							return (d.format(f / p) + "PB");
						}
					}
				}
			}
		}

	}

}
