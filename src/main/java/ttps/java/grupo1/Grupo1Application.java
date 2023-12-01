package ttps.java.grupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ttps.java.grupo1.config.StartupConfig;

@SpringBootApplication()
public class Grupo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Grupo1Application.class, args);
        StartupConfig.printStartupMessages();
    }
}
