package spring_learn;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(Principal principal) {
        String token = Jwts.builder()
                .setSubject(principal.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                .signWith(key)
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @GetMapping("/validate")
    public String validate(@RequestParam String token) {
        String username = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return "Token is valid. Welcome, " + username;
    }
}