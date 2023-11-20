package ttps.java.grupo1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + SecurityConstants.JWT_EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SIGNATURE)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SIGNATURE)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SIGNATURE).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT token");
        }
    }

}
