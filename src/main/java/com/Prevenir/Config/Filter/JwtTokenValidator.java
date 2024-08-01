package com.Prevenir.Config.Filter;

import com.Prevenir.Utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        //El token vienen en el header y se obtiene
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        //se valida si se envia el token o 
        if (jwtToken != null) {//bearer algoquenosequevatodavia
            jwtToken = jwtToken.substring(7);//apartir del 7 despues del "bearer "
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
            String username = jwtUtils.extractUser(decodedJWT);

            //Se obtienen todos los claims estan serparados por coma en un solo string
            String authoritiesClaim = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            //Se segmenta el string(si es necesario y se convierten en GrantedAuthority(en este caso es solo 1) se  convierte directo
            //Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim);    //si hay mas de 1...
            GrantedAuthority authorities = new SimpleGrantedAuthority(authoritiesClaim); //hay uno se podria hacer directo creo...
            //Setear en el Security Context Holder
            SecurityContext context = SecurityContextHolder.getContext(); //obtenemos el contexto del spring security
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, List.of(authorities));

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }

        //continua con los filtros siguientes
        filterChain.doFilter(request, response);
    }

}
