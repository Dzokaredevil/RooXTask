package RooXTask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "roox.security.jwt")
public class JwtProperties {

    private String tokenSignKey;
    private String tokenIssuer;
    private Integer tokenExpSec;
    private Integer refreshTokenExpSec;
}
