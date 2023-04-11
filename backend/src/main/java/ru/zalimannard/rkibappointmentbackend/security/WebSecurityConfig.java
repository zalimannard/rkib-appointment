package ru.zalimannard.rkibappointmentbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    @Value("${application.endpoint.registration}")
    private String registrationPath;
    @Value("${application.endpoint.persons}")
    private String personsPath;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, registrationPath).anonymous()

                        .requestMatchers(HttpMethod.GET, personsPath + "/**").authenticated()
                        .requestMatchers(HttpMethod.POST, personsPath).authenticated()
                        .requestMatchers(HttpMethod.PUT, personsPath + "/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, personsPath + "/**").hasAnyAuthority(
                                PersonRole.ADMIN.toString(), PersonRole.REGISTRAR.toString(), PersonRole.DOCTOR.toString())

                        .requestMatchers("**").hasAuthority(PersonRole.ADMIN.toString())
                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement().disable();
        return http.build();
    }

}
