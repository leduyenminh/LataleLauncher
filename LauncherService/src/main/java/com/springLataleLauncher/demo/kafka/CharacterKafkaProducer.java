package com.springLataleLauncher.demo.kafka;

// import com.SecurityService.entity.User;
import com.springLataleLauncher.demo.entity.Characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import launcher.events.CharacterEvent;

@Service
public class CharacterKafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(
      CharacterKafkaProducer.class);
  private final KafkaTemplate<String, byte[]> kafkaTemplate;

  public CharacterKafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  // public void sendUserEvent(User user) {
  //   UserEvent userEvent = UserEvent.newBuilder()
  //       .setEmail(user.getEmail().toString())
  //       .setName(user.getUsername())
  //       .setEventType("USER_CREATED")
  //       .build();

  //   try {
  //     kafkaTemplate.send("user.events", userEvent.toByteArray());
  //   } catch (Exception e) {
  //     log.error("Error sending UserCreated event: {}", userEvent);
  //   }
  // }

    public void sendCharacterEvent(Characters character, String eventType) {
    CharacterEvent characterEvent = CharacterEvent.newBuilder()
        .setCharacterId(character.getCharacterId().toString(0))
        .setName(character.getCharacterName())
        .setEventType(eventType)
        .build();

    try {
      kafkaTemplate.send("character.events", characterEvent.toByteArray());
    } catch (Exception e) {
      log.error("Error sending CharacterCreated event: {}", characterEvent);
    }
  }
}