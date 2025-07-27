package com.springdatajpa.demo.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springdatajpa.demo.entity.Characters;

@RestController
@RequestMapping("/api/v1/message")
@Slf4j
public class MessageController {
	
	private KafkaTemplate<String, Characters> kafkaTemplate;

	public MessageController(KafkaTemplate<String, Characters> kafkaTemplate){
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping("/characters")
	public ResponseEntity<String> publish(@RequestBody MessageRequest request){
		// put a log for message
		kafkaTemplate.send("characterTopic", request.message());
		return ResponseEntity.ok("Message sent successfully!");
	}
}
