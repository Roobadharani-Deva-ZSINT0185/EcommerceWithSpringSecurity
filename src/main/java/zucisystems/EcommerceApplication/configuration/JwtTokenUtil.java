package zucisystems.EcommerceApplication.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY = "OlweIh0jjU2lMbaQFVYiexwezBDBgpLGdCM3BRCCwd5mjq8mk1QgbeJrVR+VgovOD/npf+iKxekQrQmB0oi9+A==";
    private static final int TOKEN_VALIDITY = 3600 * 5;
    public String getUserNameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    //higher order function - a function which get or return a function called higher order function
    private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact()
                ;
    }
}
