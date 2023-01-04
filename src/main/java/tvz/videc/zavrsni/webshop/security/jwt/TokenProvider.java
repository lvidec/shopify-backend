package tvz.videc.zavrsni.webshop.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private final Logger log = LoggerFactory.getLogger(tvz.videc.zavrsni.webshop.security.jwt.TokenProvider.class);
    private Key key;

    private long tokenValidityInMilliseconds;

    @Value("${jwt.token-validity-seconds}")
    private int tokenValiditySeconds;

    @Value("${jwt.base64-secret}")
    private String base64Secret;

    @PostConstruct
    public void init() {
        final byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(this.base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = 1000 * this.tokenValiditySeconds;
    }

    public String createToken(final Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        final long now = (new Date()).getTime();
        final Date validity;
        validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts
          .builder()
          .setSubject(authentication.getName())
          .claim(AUTHORITIES_KEY, authorities)
          .signWith(this.key, SignatureAlgorithm.HS512)
          .setExpiration(validity)
          .compact();
    }

    public Authentication getAuthentication(final String token) {
        final Claims claims = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();

        final Collection<? extends GrantedAuthority> authorities = Arrays
          .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
          .map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());

        final User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(this.key).parseClaimsJws(authToken);
            return true;
        } catch (final JwtException | IllegalArgumentException e) {
            this.log.info("Invalid JWT token.");
            this.log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }

}
