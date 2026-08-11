package com.example.SpringBoot.exceptions;

import com.example.SpringBoot.Service.SlackNotifier;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import org.slf4j.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final SlackNotifier slackNotifier;

    public GlobalExceptionHandler(SlackNotifier slackNotifier) {
        this.slackNotifier = slackNotifier;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception e, HttpServletRequest request) {

        logger.error("Exception occurred: {}", e.getMessage(), e);

        String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
        String origin = findOriginInOurCode(e);

        String slackMessage = String.format(
                ":rotating_light: *Exception Occurred*\n" +
                        "*Type:* %s\n" +
                        "*Message:* %s\n" +
                        "*Endpoint:* %s %s%s\n" +
                        "*Location:* %s\n" +
                        "*Time:* %s",
                e.getClass().getSimpleName(),
                e.getMessage() != null ? e.getMessage() : "No message",
                request.getMethod(),
                request.getRequestURI(),
                queryString,
                origin.equals("Unknown") ? "Spring framework layer (not in our code)" : origin,
                LocalDateTime.now()
        );

        slackNotifier.send(slackMessage);

        Map<String, Object> body = Map.of(
                "status", 500,
                "error", "Internal server error",
                "message", e.getMessage() != null ? e.getMessage() : "No message"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
    private String findOriginInOurCode(Exception e) {
        for (StackTraceElement element : e.getStackTrace()) {
            if (element.getClassName().startsWith("com.example.SpringBoot")) {
                return element.getClassName() + "." + element.getMethodName()
                        + " (line " + element.getLineNumber() + ")";
            }
        }
        return "Unknown";
    }
}
