package student.examples.ggengine;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GgengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgengineApplication.class, args);
	}
	
	

}
