package ProducerConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
  
  @Bean
  public NewTopic userTopic() {
    return TopicBuilder.name("user.events")
            .partitions(3)
            .replicas(3)
            .compact()    // keep latest state per userId (good for user profile updates)
            .build();
  }

	@Bean
  public NewTopic characterTopic() {
    return TopicBuilder.name("character.events")
            .partitions(6)
            .replicas(3)
            .config("cleanup.policy", "delete") // or compact if you want latest state
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000)) // keep 7 days
            .build();
  }

  @Bean
  public NewTopic auditTopic() {
    return TopicBuilder.name("audit.events")
            .partitions(6)
            .replicas(3)
            .config("retention.ms", String.valueOf(30L * 24 * 60 * 60 * 1000)) // keep 30 days
            .build();
  }
}
