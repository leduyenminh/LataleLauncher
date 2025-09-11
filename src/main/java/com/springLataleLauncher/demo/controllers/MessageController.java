package com.springLataleLauncher.demo.controllers;


//import com.springLataleLauncher.demo.vo.CharacterRequest;
import com.springLataleLauncher.demo.kafka.KafkaProducerConfig;
import com.springLataleLauncher.demo.vo.CharacterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springLataleLauncher.demo.entity.Characters;

@RestController
@RequestMapping("/api/v1/message")
@Slf4j
public class MessageController {

	private final KafkaProducerConfig kafkaProducerConfig;

	@Autowired
	public MessageController(KafkaProducerConfig kafkaProducerConfig){
        this.kafkaProducerConfig = kafkaProducerConfig;
	}

	@PostMapping("/characters")
	public ResponseEntity<String> publish( @RequestBody CharacterVO request){
		// put a log for message
		ProducerFactory<String, Object> producerFactory =  kafkaProducerConfig.producerFactory();
		KafkaTemplate<String, Object> kafkaTemplate = kafkaProducerConfig.kafkaTemplate(producerFactory);
		kafkaTemplate.send("character.events", request);
		return ResponseEntity.ok("Message sent successfully!");
	}
}
