package edu.eci.ieti.proyecto.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private long expiration;

    public String getSecret(){return this.secret;}
    public void setSecret(String secret){this.secret=secret;}

    
}
