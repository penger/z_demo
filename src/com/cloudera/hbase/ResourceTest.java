package com.cloudera.hbase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceTest {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = ResourceTest.class.getClassLoader().getResourceAsStream("hbase-site.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line =null;
		while((line=reader.readLine())!=null){
			System.out.println(line);
		}

	}

}
