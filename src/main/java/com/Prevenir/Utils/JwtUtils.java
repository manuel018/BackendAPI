package com.Prevenir.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    //Credenciales para firmar el token contraseña privada
    @Value("${security.jwt.key.private}")
    private String privateKey;

    //Credenciales para firmar el token usuario generador
    @Value("${security.jwt.iss.generator}")
    private String userGenerator;

    public String createToken(Authentication authentication) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            //Usuario que se logea...
            String username = authentication.getPrincipal().toString();
            //Permisos
            String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));//En este caso sera los roles
            String JWToken = JWT.create()
                    .withIssuer(this.userGenerator) //Firma de quien creo el token en este caso la empresa
                    .withSubject(username) //para quien se genero el token
                    .withIssuedAt(new Date()) //cuando se genero el token
                    .withClaim("authorities", authorities)//manejar de acuerdo a logica
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) //cuando vence el token se asigno 30 mins en este caso
                    .withJWTId(UUID.randomUUID().toString()) //identificador unico del token generado aleatoriamente
                    .withNotBefore(new Date(System.currentTimeMillis())) //valido desde cuando (al momento de crearse)
                    .sign(algorithm); //firma con la secretkey
            return JWToken;
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            //Por ahora nada
            throw new JWTCreationException("Error al generar token!", exception);
        }
    }

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            //Verificador del token con las firmas del generador del token es decir el backend y la secretkeyS
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.userGenerator).build();
            //validacion del token
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("No autorizado: Token invalido!");
        }
    }

    //obtener username si es necesario...
    public String extractUser(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    //Obtener un claim especifico para mas adelante
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

}
