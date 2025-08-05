package com.example.app.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	@Value("${my-secret-key}")
	private String sercretKey;



//	public String generateSecretKey() {
//		try {
//			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//			SecretKey secretKey = keyGen.generateKey();
//			System.out.println("Secret Key : " + secretKey.toString());
//			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException("Error generating secret key", e);
//		}
//	}

	public String generateToken(UserDetails user) {

		Map<String, Object> claims = new HashMap<>();

		claims.put("roles",
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

		return Jwts.builder().setClaims(claims).setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(sercretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {

		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	public List<String> extractRoles(String token) {
		Claims claims = extractAllClaims(token);
		return claims.get("roles", List.class);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);

	}

}
