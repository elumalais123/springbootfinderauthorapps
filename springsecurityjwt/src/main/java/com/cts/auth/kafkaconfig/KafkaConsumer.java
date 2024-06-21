package com.cts.auth.kafkaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cts.auth.dto.UserRegistration;

@Component
public class KafkaConsumer {

	private Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "AuthorTopic", groupId = "AuthorGroup")
	public void consumeJson(UserRegistration userRegistration) {
		log.info(String.format("Consuming the message from AuthorTopic :: %s", userRegistration.toString()));
		
	}
}
