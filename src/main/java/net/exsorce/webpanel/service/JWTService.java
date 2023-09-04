package net.exsorce.webpanel.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
@Service
public class JWTService
{

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;

	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;

	@Value("${application.security.jwt.refresh-token.expiration}")
	private long refreshExpiration;

	public String extractUserData ( String token )
	{
		return extractClaim( token, Claims::getSubject );
	}

	public <T> T extractClaim ( String token , Function<Claims, T> claimsResolver )
	{
		final Claims claims = extractAllClaims( token );
		return claimsResolver.apply( claims );
	}

	public String generateToken ( UserDetails details )
	{
		return generateToken( new HashMap<>(), details );
	}

	public String generateToken ( Map<String, Object> extras, UserDetails details )
	{
		 return buildToken(extras, details, jwtExpiration);
	}

	public String generateRefreshToken ( UserDetails userDetails )
	{
		return buildToken(new HashMap<>(), userDetails, refreshExpiration);
	}

	public boolean isValid ( String token, UserDetails details )
	{
		final String userdata = extractUserData( token );
		return ( userdata.equals( details.getUsername() ) ) && !isExpired( token );
	}

	private String buildToken( Map<String, Object> extraClaims, UserDetails userDetails, long expiration ) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private boolean isExpired ( String token )
	{
		return extractExpiration( token ).before( new Date() );
	}

	private Date extractExpiration ( String token )
	{
		return extractClaim( token, Claims::getExpiration );
	}

	private Claims extractAllClaims ( String token )
	{
		return Jwts
				.parserBuilder()
				.setSigningKey( getSignInKey() )
				.build()
				.parseClaimsJws( token )
				.getBody();
	}

	private Key getSignInKey ()
	{
		byte[] keyBytes = Decoders.BASE64.decode( secretKey );
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
