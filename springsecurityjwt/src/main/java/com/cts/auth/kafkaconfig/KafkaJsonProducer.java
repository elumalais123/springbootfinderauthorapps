package com.cts.auth.kafkaconfig;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.cts.auth.dto.UserRegistration;
import com.cts.auth.entity.User;

@Service
//@RequiredArgsConstructor
public class KafkaJsonProducer {

	private  KafkaTemplate<String, UserRegistration> kafkaTemplate;

	public KafkaJsonProducer(KafkaTemplate<String, UserRegistration> kafkaTemplate) {
			this.kafkaTemplate=kafkaTemplate;
	}
	
	public void sendMessage(UserRegistration userRegistration) {

		Message<UserRegistration> message = MessageBuilder
									.withPayload(userRegistration)
									.setHeader(KafkaHeaders.TOPIC, "AuthorTopic")
									.build();

		kafkaTemplate.send(message);

	}

}