package com.greenrent.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.greenrent.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

    private static Logger logger=LoggerFactory.getLogger(JwtUtils.class);

    @Value("${greenrent.app.jwtSecret}")
    private String jwtSecret;

    @Value("${greenrent.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userDetails=   (UserDetailsImpl)  authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(""+(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public Long getIdFromJwtToken(String token) {
        String strId= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(strId);
    }

    public boolean validateJwtToken(String token) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token is expired {}",e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT Token is unsupported {}",e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT Token is malformed {}",e.getMessage());
        } catch (SignatureException e) {
            logger.error(" Invalid JWT signature {}",e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT Token illegal args {}",e.getMessage());
        }

        return false;

    }


}