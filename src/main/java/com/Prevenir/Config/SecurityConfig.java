package com.Prevenir.Config;

import com.Prevenir.Config.Filter.JwtTokenValidator;
import com.Prevenir.Service.AdministrativoService;
import com.Prevenir.Utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csfr -> csfr.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    //Seguridad de enpoints segun la logica de la app
                    http.requestMatchers(HttpMethod.POST, "/api/administrativo").hasAnyRole("Admin");//Rol de mayor jerarquia el dueño o gerente creador de usuarios adminsitrativos
                    http.requestMatchers(HttpMethod.PATCH, "/api/administrativo").hasAnyRole("Admin");//Rol de mayor jerarquia el dueño o gerente creador de usuarios adminsitrativos
                    http.requestMatchers(HttpMethod.PUT, "/api/administrativo").hasAnyRole("Admin");//Rol de mayor jerarquia el dueño o gerente creador de usuarios adminsitrativos
                    http.requestMatchers(HttpMethod.DELETE, "/api/administrativo").hasAnyRole("Admin");//Rol de mayor jerarquia el dueño o gerente creador de usuarios adminsitrativos
                    //Endpoint público login para todos
                    http.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                    //http.anyRequest().authenticated(); // o denyall
                    http.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)//Muy importante se debe validar antes del filtro de autenticacion
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(AdministrativoService adminService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEnconder());
        provider.setUserDetailsService(adminService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEnconder() {
        return new BCryptPasswordEncoder();
    }

}
