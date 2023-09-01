package util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import network.ClientDiscovery
import util.Globals.JWT_SIGNING_KEY
import java.util.*

object JwtUtils {
    fun buildJwt(address: String, displayName: String): String
    = Jwts.builder()
        .setSubject(address)
        .setIssuedAt(Date())
        .claim("name", displayName)
        .signWith(Keys.hmacShaKeyFor(JWT_SIGNING_KEY.toByteArray()))
        .compact()


    fun getJWTClaims(jwt: String): Claims
    = Jwts.parserBuilder()
        .setSigningKey(JWT_SIGNING_KEY.toByteArray())
        .build()
        .parseClaimsJws(jwt)
        .body
}