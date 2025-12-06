package com.kafkaService.KafkaConfig;

public class CharacterEvent {
	String eventId;
	String characterEvent;

	CharacterEvent(String eventId, String characterEvent){
		this.eventId = eventId;
		this.characterEvent = characterEvent;
	}
}
