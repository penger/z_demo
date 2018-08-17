package com.jike.nameservice;

import com.jike.nameservice.IdMaker.RemoveMethod;

public class TestIdMaker {

	public static void main(String[] args) throws Exception {
		
		IdMaker idMaker = new IdMaker("192.168.8.211:2181",
				"/NameService/IdGen", "ID");
		idMaker.start();

		try {
			for (int i = 0; i < 1000; i++) {
				String id = idMaker.generateId(RemoveMethod.DELAY);
				System.out.println(id);

			}
		} finally {
			idMaker.stop();

		}
	}

}
