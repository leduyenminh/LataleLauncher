import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories("com.springLataleLauncher.demo.repository")
@EntityScan("com.springLataleLauncher.demo.entity")
@EnableCaching
@EnableAsync
public class SecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(LauncherApplication.class, args);
	}

}
