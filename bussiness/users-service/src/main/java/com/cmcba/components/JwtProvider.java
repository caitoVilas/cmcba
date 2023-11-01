package com.cmcba.components;

import com.cmcba.api.models.MainUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(mainUser.getUsername())
                .claim("roles", this.getRolesFromToken(mainUser))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(this.getKey(secret))
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;
        }catch (MalformedJwtException e){
            log.error("token mal formado");
        }catch (UnsupportedJwtException e){
            log.error("token no soportado");
        }catch (ExpiredJwtException e){
            log.error("token expirado");
        }catch (IllegalArgumentException e){
            log.error("token vacio");
        }catch (SignatureException e){
            log.error("fallo en la firma");
        }
        return false;
    }

    private List<String> getRolesFromToken(MainUser mainUser){
        return mainUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private Key getKey(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
