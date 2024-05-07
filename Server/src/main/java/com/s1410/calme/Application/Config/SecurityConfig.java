package com.s1410.calme.Application.Config;
import com.s1410.calme.Application.Filters.JwtAuthenticationFilter;
import com.s1410.calme.Application.Security.CustomUserDetailsService;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Utils.RolesEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    private static final String[] FREE_ENDPOINTS = {
            "/api/v1/company/create",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            //others
            "/authenticate/**",
            "/api/security/auth/**", "/styles/**", "/assets/**", "/scripts/**",
            //login
            "/login",
            //register
            "/assistent/register",
            "/doctor/register",
            //Endpoint para obtener lista de doctores y mostrar en front
            "/doctor/all/{active}",
            //Endpoint para obtener un doctor por su especialidad
            "/doctor/findBySpecialty",
            //Endpoint para eliminar y buscar un doctor por su id
            "/doctor/id/**",
            //Endpoint para buscar un doctor por su codigo postal
            "/doctor/findByPostalCode",
            "/actuator/**",
            "/db/**",
            "/email/emailValidation/**"
    };

    private static final String[] DOCTOR_ENDPOINTS = {
            "/doctor/update", //todo. ver tema delete doctor desde el controller para que no sea público.
            "/whatsapp/reminder",
            "/appointment/all",
            "/appointment/betweenDates",
            "/appointment/doctor/**",
            "/appointment/date/**"
    };

    private static final String[] ASSISTENT_ENDPOINTS = {
            "/doctor/findByAvailability/**",
            "/doctor/findByPostalCode",
            "/doctor/findBySurname/**",
            "/assistent/id/**", //toconsider. ver si este endpoint es accesible al dr para ver el paciente.
            "/assistent/update",
            "/assisted/register",
            "/assisted/id/**", //toconsider. ver si este endpoint es accesible al dr para ver el paciente.
            "/assisted/all/**",
            "/assisted/update",
            "/assisted/updateRelation",
            "/assisted/deleteRelation/**",
            "/appointment/register",
            "/appointment/all",
            "/appointment/betweenDates",
            "/appointment/assistent/**",
            "/appointment/assisted/**",
            "/appointment/date/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable).cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(request ->
                        request.requestMatchers(FREE_ENDPOINTS).permitAll()
                                .requestMatchers(DOCTOR_ENDPOINTS).hasRole("DOCTOR")
                                .requestMatchers(ASSISTENT_ENDPOINTS).hasRole("ASSISTENT")
                                .anyRequest().authenticated()
                )
                //authRequest.anyRequest().permitAll())
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
