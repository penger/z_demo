package com.happpy.kafka;


import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.Properties;


import kafka.consumer.ConsumerConfig;

import kafka.consumer.ConsumerIterator;

import kafka.consumer.KafkaStream;

import kafka.javaapi.consumer.ConsumerConnector;

import kafka.serializer.StringDecoder;

import kafka.utils.VerifiableProperties;

public class TestConsumer {
private final ConsumerConnector consumer;

    

    private TestConsumer(){

        Properties props = new Properties();

        //zookeeper ����

        props.put("zookeeper.connect", "node3:2181");

        //group ����һ�������� ��������ָ��

        props.put("group.id", "abc");

        //zk���ӳ�ʱ

//        props.put("zookeeper.session.timeout.ms", "4000");

//        props.put("zookeeper.sync.time.ms", "200");

//        props.put("auto.commit.interval.ms", "1000");

//        props.put("auto.offset.reset", "smallest");

        //���л���

        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);

    }

    

    void consume(){

        Map<String,Integer> topicCountMap =new HashMap<String,Integer>();

        topicCountMap.put("my-replicated-topic", 1    );

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());

        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =

                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);

//        KafkaStream<String, String> stream = consumerMap.get("kafkaToptic","kafkaToptic").get(0);

        KafkaStream<String, String> stream = consumerMap.get("my-replicated-topic").get(0);

        ConsumerIterator<String, String> it = stream.iterator();

        while (it.hasNext())

            System.out.println(it.next().message());

    }

    

    public static void main(String[] args) {

        new TestConsumer().consume();

    }
}
