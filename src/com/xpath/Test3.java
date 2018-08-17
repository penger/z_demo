package com.xpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test3 {
	
	private static String path="d://logs";	
	private static String new_file ="d://h.txt";

	public static void main(String[] args) throws Exception {
		File x = new File(new_file);
		BufferedWriter writer = new BufferedWriter(new FileWriter(x,true));
		File file = new File(path);
		String[] list = file.list();
		System.out.println(list.length);
		System.out.println(list[2]);
		for (String folder : list) {
			happy(writer,folder);
		}
		writer.close();
	}

	private static void happy(BufferedWriter writer,String folder) throws Exception {
		System.out.println(folder+"------------------------");
		File file = new File(path+"//"+folder);
		int size =file.list().length;
		if(size==1) {
			File info = new File(path+"//"+folder+"//info.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(info));
			String readLine = bufferedReader.readLine();
			if(readLine!=null) {
				System.out.println("line"+readLine);
				writer.write(readLine);
				writer.write("/n");
			}
			bufferedReader.close();
			info.delete();
			System.out.println(path+"//"+folder +"             ---- delete");
			File fooo = new File(path+"//"+folder);
			fooo.delete();
		}else {
			System.out.println("has pic");
		}
		
	}
	
	

}
