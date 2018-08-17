package com.demo.es.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.demo.es.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

	public static void main(String[] args) throws UnknownHostException, JsonProcessingException {
		
		Builder builder = Settings.builder();
		builder.put("transport.type","netty3")
		.put("http.type", "netty3");
		Settings settings = builder.build();
		// on startup
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		TransportClient client = new PreBuiltTransportClient(settings)
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("node1"), 9300));

		User user = new User("张三", 1, "打球");
		User user2 = new User("李四", 1, "打dota");
		IndexResponse response = client.prepareIndex("happy","user").setSource(addUser(user2)).get();
		System.out.println(response);
		System.out.println(addUser(user2));

		client.close();
	}

	private static byte[] addUser(User user) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		byte[] jsonBytes = mapper.writeValueAsBytes(user);
		return jsonBytes;
		
	}

}
