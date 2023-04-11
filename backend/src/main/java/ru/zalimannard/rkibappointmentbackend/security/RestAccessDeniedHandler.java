package ru.zalimannard.rkibappointmentbackend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.io.IOException;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println(
                "{ \"httpCode\": \"" + HttpCodes.FORBIDDEN.getCode() + "\"\n," +
                        "\"code\": \"ss-002\"\n," +
                        "\"message\": \"" + "Access denied" + "\" }"
        );
    }

}
