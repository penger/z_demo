package com.cloudera.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

public class HBaseTest {
	
	static Configuration conf;
	static Connection connection;
	
	static{
		System.setProperty("hadoop.home.dir", "E:\\opensoft\\hadoop-2.6.0-cdh5.5.0\\");
		conf = HBaseConfiguration.create();
		conf.addResource(HBaseTest.class.getClassLoader()
				.getResourceAsStream("bak/hbase-site.xml"));
	}

	public static void main(String[] args) throws IOException {
		init();
//		dropTable();
//		createTable();
//		testListTabel();
		insertRecordsIntoTable();
	}
	
	
	public static void init(){
		try {
			connection = ConnectionFactory.createConnection(conf);
			System.out.println(connection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void dropTable() throws IOException{
		Admin admin = connection.getAdmin();
		TableName tableName = TableName.valueOf("test");
//		HTableDescriptor desc = new HTableDescriptor(tableName);
//		HColumnDescriptor family= new HColumnDescriptor("cf");
//		desc.addFamily(family);
		admin.disableTable(tableName);
		admin.deleteTable(tableName);
	}
	
	
	
	public static void createTable() throws IOException{
		Admin admin = connection.getAdmin();
		TableName tableName = TableName.valueOf("test");
//		tableName.
		HTableDescriptor desc = new HTableDescriptor(tableName);
		HColumnDescriptor family= new HColumnDescriptor("cf");
		desc.addFamily(family);
		admin.createTable(desc);
	}
	
	public static void insertRecordsIntoTable() throws IOException{
		Table table = connection.getTable(TableName.valueOf("test"));
		List<Put> list = new ArrayList<>();
		for (int i=0;i<100;i++){
			
			Put put =new Put(("row"+i).getBytes());
			put.addColumn("cf".getBytes(), "age".getBytes(), ("age"+i).getBytes());
			list.add(put);
		}
		table.put(list);
	}
	
	private static void testListTabel() throws IOException {
		Admin admin = connection.getAdmin();
		TableName[] listTableNames = admin.listTableNames();
		for (TableName tableName : listTableNames) {
			System.out.println(tableName.getNameAsString());
		}
		
	}
	
	

}
