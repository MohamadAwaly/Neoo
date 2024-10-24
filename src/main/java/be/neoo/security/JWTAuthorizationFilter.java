package be.neoo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;

import java.io.IOException;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt =request.getHeader("Authorization");
        if (jwt==null || !jwt.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
        jwt = jwt.substring(SecParams.PREFIX.length());
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String username = decodedJWT.getSubject();

        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        List<String> permissions = decodedJWT.getClaims().get("permissions").asList(String.class);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);

    }
}
