package ProducerConfig;

import com.springLataleLauncher.demo.entity.Characters;
import com.springLataleLauncher.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerConfig {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

		@KafkaListener(topics = "user.events",
									groupId = "groupId")
		public void listenerUserTopic(User user){
			//do something other than logging
			logger.info("Listener data: "+ user.toString());
		}

		@KafkaListener(topics = "character.events",
								groupId = "groupId")
		public void listenerCharacterTopic(Characters characters){
			//do something other than logging
			logger.info("Listener data: "+ characters.toString());
		}
}
