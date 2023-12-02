package ttps.java.grupo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ttps.java.grupo1.service.ExpenseService;
import ttps.java.grupo1.service.UserService;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Configuration
@EnableJpaRepositories(basePackages = "ttps.java.grupo1.repository")
public class SpringDataConfig {

    @Bean
    @Scope(scopeName = SCOPE_SINGLETON)
    public UserService springDataJpaServiceUser() {
        return new UserService();
    }

    @Bean
    @Scope(scopeName = SCOPE_SINGLETON)
    public ExpenseService springDataJpaServiceExpense() {
        return new ExpenseService();
    }

}
