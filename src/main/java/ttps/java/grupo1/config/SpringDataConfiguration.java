package ttps.java.grupo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ttps.java.grupo1.service.UserService;

@Configuration
@EnableJpaRepositories(basePackages = "ttps.java.grupo1.DAO")
public class SpringDataConfiguration {

    @Bean
    public UserService springDataJpaService() {
        return new UserService();
    }


}
