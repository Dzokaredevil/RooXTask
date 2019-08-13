package RooXTask.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import RooXTask.config.JwtProperties;

public class JwtAuthProvider implements AuthenticationProvider {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Метод пробует аутентифицироваться по JWT.
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        try {
            String token = ((JwtAuthToken) authentication).getToken();
            Jwt jwt = JwtHelper.decodeAndVerify(token, new MacSigner(jwtProperties.getTokenSignKey()));
            ObjectMapper om = new ObjectMapper();
            JwtAuthToken auth = om.readValue(jwt.getClaims(), JwtAuthToken.class);
            ((JwtAuthToken) authentication).setId(auth.getId());
            ((JwtAuthToken) authentication).setName(auth.getName());
            ((JwtAuthToken) authentication).setPrincipal(auth.getId());
            ((JwtAuthToken) authentication).setRoles(auth.getRoles());
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        } catch (Exception e) {
            throw new JwtAuthException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}