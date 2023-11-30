package ttps.java.grupo1;

import ch.qos.logback.classic.html.UrlCssBuilder;
import org.apache.logging.log4j.spi.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ttps.java.grupo1.config.StartupConfig;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Grupo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Grupo1Application.class, args);
        StartupConfig.printStartupMessages();
    }
}
