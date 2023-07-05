package ru.javabegin.micro.demo.eurekaclient;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.javabegin.micro.demo.eurekaclient.repository.UserDto;

@EnableKafka
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientApplication {

	@KafkaListener(topics = "msg")
	public void orderListener(ConsumerRecord<Double, UserDto> record) {
		System.out.println(record.partition());
		System.out.println(record.key());
		System.out.println(record.value());
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}
