package paidagogos.speck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpeckApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpeckApplication.class, args);
	}
}
