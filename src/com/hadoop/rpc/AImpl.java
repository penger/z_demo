package com.hadoop.rpc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AImpl implements AProtocol {

	@Override
	public String getDate(String s) {
		System.out.println("发现调用参数："+s);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date());
	}

}
