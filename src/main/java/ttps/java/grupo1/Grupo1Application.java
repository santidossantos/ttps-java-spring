package ttps.java.grupo1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ttps.java.grupo1.seeders.GroupSeeds;

@SpringBootApplication
public class Grupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Grupo1Application.class, args);
	}

	@Autowired
	private GroupSeeds groupSeeds;

	@Value("${SEED:FALSE}")
	private String seedValue;

	@PostConstruct
	public void init() {
		if (seedValue.equals("true")) {
			groupSeeds.seed();
		}
	}

}
