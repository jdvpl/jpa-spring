package com.jdvpl.backend.services;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.repositories.entity.UserEntity;

import io.jsonwebtoken.Claims;
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

    /**
     * Generates a JSON Web Token (JWT) for the given user with the provided extra claims.
     *
     * @param  user        the user for whom the token is generated
     * @param  extraClaims additional claims to include in the token
     * @return             the generated JWT as a string
     */
    public String generateToken(UserEntity user, Map<String, Object> extraClaims) {
        Date issuedADate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(issuedADate.getTime()+(EXPIRATION_MINUTES*60*1000));
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(issuedADate)
            .setExpiration(expirationDate)
            .setHeaderParam(Header.TYPE, Header.TYPE)
            .signWith(generateKey(),SignatureAlgorithm.HS384)
            .compact();
    }

       /**
    * Generates a secret key for HMAC-SHA encryption.
    *
    * @return a Key object representing the generated secret key
    */
    private Key generateKey() {
       byte[] secretAsBytes= Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(secretAsBytes);
    }
        /**
     * Extracts the username from a JWT token.
     *
     * @param  jwt  the JWT token from which to extract the username
     * @return      the extracted username
     */
    public String extractUsername(String jwt) {
        return extactAllClaims(jwt)
            .getSubject();
        }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param  jwt  the JWT token to extract claims from
     * @return      the claims extracted from the JWT token
     */
    private Claims extactAllClaims(String jwt) {
        return Jwts.parserBuilder()
            .setSigningKey(generateKey())
            .build()
            .parseClaimsJws(jwt).getBody();
    }
}