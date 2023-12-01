package ttps.java.grupo1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Obtener el token del encabezado de autorización
        String token = getJwtFromRequest(request);

        // Validar el token
        if (token != null && jwtService.validateToken(token)) {
            return true; // Continuar con la solicitud si el token es válido
        } else {
            System.out.println("Token no válido");
            System.out.println("Token: " + token);
            // Devolver un código de error no autorizado si el token no es válido
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Puedes implementar otros métodos del Interceptor según sea necesario
}
