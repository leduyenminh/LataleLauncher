package com.springdatajpa.demo.kafka;

import org.springframework.stereotype.Component;

import javax.xml.stream.events.Characters;

@Component
public class KafkaListener {
	@KafkaListener(topics = "charactersTopic",
								groupId = "groupId")
	void listener(Characters character){
		System.err.println("Listener data: "+ character.toString());
	}
}
