package com.springLataleLauncher.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
  
  @Bean
  public NewTopic userTopic() {
    return new NewTopic("user.events", 3, (short) 1);
  }

	@Bean
  public NewTopic characterTopic() {
    return TopicBuilder.name("character.events").build();
  }
}
