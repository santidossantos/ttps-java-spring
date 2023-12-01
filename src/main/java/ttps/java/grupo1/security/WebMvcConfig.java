package ttps.java.grupo1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Agrega el interceptor y especifica las rutas que deben ser interceptadas
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/users/**"); // Cambia "/api/**" por el patrón que desees proteger
    }
}
