package com.springdatajpa.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
  
  @Bean
  public NewTopic charactersTopic() {
    return TopicBuilder.name("charactersTopic").build();
  }
}
