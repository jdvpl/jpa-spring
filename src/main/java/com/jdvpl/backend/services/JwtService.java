package com.jdvpl.backend.services;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.repositories.entity.UserEntity;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private  String SECRET_KEY ;

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;


  

    public String generateToken(UserEntity user, Map<String, Object> extraClaims) {
        Date issuedADate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(issuedADate.getTime()+(EXPIRATION_MINUTES*60*100));
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(issuedADate)
            .setExpiration(expirationDate)
            .setHeaderParam(Header.TYPE, Header.TYPE)
            .signWith(generateKey(),SignatureAlgorithm.HS384)
            .compact();
    }




    private Key generateKey() {
       byte[] secretAsBytes= Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(secretAsBytes);
    }
}