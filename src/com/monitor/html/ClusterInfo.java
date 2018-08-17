package com.monitor.html;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class ClusterInfo implements Serializable {
	
	/**
	 * 用于序列化
	 */
	private static final long serialVersionUID = -2858578155608147042L;

	public String insert_date;

	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}

	private static ObjectMapper mapper = new ObjectMapper();

	private String cluster_name = "cluster_name";

	private String namenode_status = "namenode_status";
	private String storage_status = "storage_status";
	private String datanode_status = "datanode_status";
	private String namenode_jvm = "namenode_jvm";
	private String datanode_jvm = "datanode_jvm";

	private String hmaster_status = "hmaster_status";
	private String regionserver_status = "regionserver_status";
	
	private String region_min = "region_min";
	private String region_avg = "region_avg";
	private String region_max = "region_max";
	
	private String request_min = "request_min";
	private String request_avg = "request_avg";
	private String request_max = "request_max";
	
	private String heap_min = "heap_min";
	private String heap_avg = "heap_avg";
	private String heap_max = "heap_max";
	private String heap_set = "heap_set";


	public String getCluster_name() {
		return cluster_name;
	}


	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}


	public String getNamenode_status() {
		return namenode_status;
	}


	public void setNamenode_status(String namenode_status) {
		this.namenode_status = namenode_status;
	}


	public String getStorage_status() {
		return storage_status;
	}


	public void setStorage_status(String storage_status) {
		this.storage_status = storage_status;
	}


	public String getDatanode_status() {
		return datanode_status;
	}


	public void setDatanode_status(String datanode_status) {
		this.datanode_status = datanode_status;
	}


	public String getNamenode_jvm() {
		return namenode_jvm;
	}


	public void setNamenode_jvm(String namenode_jvm) {
		this.namenode_jvm = namenode_jvm;
	}


	public String getDatanode_jvm() {
		return datanode_jvm;
	}


	public void setDatanode_jvm(String datanode_jvm) {
		this.datanode_jvm = datanode_jvm;
	}


	public String getHmaster_status() {
		return hmaster_status;
	}


	public void setHmaster_status(String hmaster_status) {
		this.hmaster_status = hmaster_status;
	}


	public String getRegionserver_status() {
		return regionserver_status;
	}


	public void setRegionserver_status(String regionserver_status) {
		this.regionserver_status = regionserver_status;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public String getRegion_min() {
		return region_min;
	}


	public void setRegion_min(String region_min) {
		this.region_min = region_min;
	}


	public String getRegion_avg() {
		return region_avg;
	}


	public void setRegion_avg(String region_avg) {
		this.region_avg = region_avg;
	}


	public String getRegion_max() {
		return region_max;
	}


	public void setRegion_max(String region_max) {
		this.region_max = region_max;
	}


	public String getRequest_min() {
		return request_min;
	}


	public void setRequest_min(String request_min) {
		this.request_min = request_min;
	}


	public String getRequest_avg() {
		return request_avg;
	}


	public void setRequest_avg(String request_avg) {
		this.request_avg = request_avg;
	}


	public String getRequest_max() {
		return request_max;
	}


	public void setRequest_max(String request_max) {
		this.request_max = request_max;
	}


	public String getHeap_min() {
		return heap_min;
	}


	public void setHeap_min(String heap_min) {
		this.heap_min = heap_min;
	}


	public String getHeap_avg() {
		return heap_avg;
	}


	public void setHeap_avg(String heap_avg) {
		this.heap_avg = heap_avg;
	}


	public String getHeap_max() {
		return heap_max;
	}


	public void setHeap_max(String heap_max) {
		this.heap_max = heap_max;
	}


	public String getHeap_set() {
		return heap_set;
	}


	public void setHeap_set(String heap_set) {
		this.heap_set = heap_set;
	}


	@Override
	public String toString() {
		return "ClusterInfo [cluster_name=" + cluster_name
				+ ", namenode_status=" + namenode_status + ", storage_status="
				+ storage_status + ", datanode_status=" + datanode_status
				+ ", namenode_jvm=" + namenode_jvm + ", datanode_jvm="
				+ datanode_jvm + ", hmaster_status=" + hmaster_status
				+ ", regionserver_status=" + regionserver_status
				+ ", region_min=" + region_min + ", region_avg=" + region_avg
				+ ", region_max=" + region_max + ", request_min=" + request_min
				+ ", request_avg=" + request_avg + ", request_max="
				+ request_max + ", heap_min=" + heap_min + ", heap_avg="
				+ heap_avg + ", heap_max=" + heap_max + ", heap_set="
				+ heap_set + "]";
	}


	public String toJsonStr(){
		String info = "";
		try {
			info = mapper.writeValueAsString(this);
			System.out.println(info);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return info;
	}


	

}
