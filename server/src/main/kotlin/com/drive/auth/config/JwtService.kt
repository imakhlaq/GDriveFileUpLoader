package com.drive.auth.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.time.LocalDateTime
import java.util.*


@Service
class JwtService {

    val SECRET_KEY = "dajjklfjsklfjsklfopafopaopafklsjfklsfjklsfj123131313131"


    fun extractUserName(token: String): String? {

        val allClaims = extractAllClaims(token)
        return allClaims.subject;
    }

    private fun extractAllClaims(token: String): Claims {

        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token).body;
    }


    private fun getSignInKey(): Key {

        val keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(userDetails: UserDetails): String {

        return Jwts.builder()
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 24))
            .signWith(getSignInKey())
            .compact();
    }


    //user details passed by filter
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {

        val userName = extractUserName(token);
        return userName.equals(userDetails.username) && isTokenValid(token);

    }

    private fun isTokenValid(token: String): Boolean {

        val allClaims = extractAllClaims(token);
        //extract expiration date and check
        return !allClaims.expiration.before(Date());
    }

}