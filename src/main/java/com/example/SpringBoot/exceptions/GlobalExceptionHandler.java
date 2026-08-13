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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException e) {
        Map<String, Object> body = Map.of(
                "status", 404,
                "error", "Not Found",
                "message", e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception e, HttpServletRequest request) {

        logger.error("Exception occurred: {}", e.getMessage(), e);

        String slackMessage = "*Exception Details:*\n```" + buildExceptionDetails(e) + "```";
        slackNotifier.send(slackMessage);

        Map<String, Object> body = Map.of(
                "status", 500,
                "error", "Internal server error",
                "message", e.getMessage() != null ? e.getMessage() : "No message"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
    private String buildExceptionDetails(Exception e) {
        StringBuilder sb = new StringBuilder();

        sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");

        for (StackTraceElement element : e.getStackTrace()) {
            if (element.getClassName().startsWith("com.example.SpringBoot")) {
                sb.append("    at ").append(element.toString()).append("\n");
            }
        }

        Throwable cause = e.getCause();
        if (cause != null) {
            sb.append("Caused by:\n");
            sb.append(cause.getClass().getName()).append(": ").append(cause.getMessage()).append("\n");
        }

        return sb.toString();
    }
}
