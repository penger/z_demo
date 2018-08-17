package com.happpy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import us.codecraft.webmagic.selector.Html;

public class Rsource2String {

	public static void main(String[] args) {
		String zString=getS("z");
		System.out.println(zString);
		toHtml(zString, "C:\\Users\\gongp\\Desktop\\info.html");
	}

	public static String getS(String resource) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Rsource2String.class.getClassLoader().getResourceAsStream(resource)));
			String line = null;
			while((line=reader.readLine())!=null){
				sb.append(line+"\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	
	public static void toHtml(String str,String path){
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
