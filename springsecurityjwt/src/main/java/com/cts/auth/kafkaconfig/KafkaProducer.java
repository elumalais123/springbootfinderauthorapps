package com.cts.auth.kafkaconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cts.auth.dto.UserRegistration;

@Component
//@RequiredArgsConstructor
public class KafkaProducer {
	
	private Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	private  KafkaTemplate<String, UserRegistration> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, UserRegistration> kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	public void sendMessage(final UserRegistration userRegistration) {
		log.info("sending message to AuthorTopic {}", userRegistration);
		kafkaTemplate.send("Topic1", userRegistration);
	}

	
	
}