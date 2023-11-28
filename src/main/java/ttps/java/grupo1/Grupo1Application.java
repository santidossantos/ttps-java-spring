package ttps.java.grupo1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import ttps.java.grupo1.seeders.GroupCategorySeeds;
import ttps.java.grupo1.seeders.GroupSeeds;

@SpringBootApplication
public class Grupo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Grupo1Application.class, args);
	}

	@Autowired
	private GroupSeeds groupSeeds;

	@Autowired
	private GroupCategorySeeds groupCategorySeeds;

	@Value("${SEED:FALSE}")
	private String seedValue;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		if (seedValue.equals("true")) {
			groupCategorySeeds.seed();
			groupSeeds.seed();
		}
	}

}
