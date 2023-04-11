package ru.zalimannard.rkibappointmentbackend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.io.IOException;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(
                "{ \"httpCode\": \"" + HttpCodes.UNAUTHORIZED.getCode() + "\"\n," +
                        "\"code\": \"ss-001\"\n," +
                        "\"message\": \"" + "Access denied" + "\" }"
        );
    }

}