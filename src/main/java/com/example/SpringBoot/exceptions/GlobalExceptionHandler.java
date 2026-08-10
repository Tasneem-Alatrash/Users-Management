package com.example.SpringBoot.exceptions;

import com.example.SpringBoot.Service.SlackNotifier;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final SlackNotifier slackNotifier;

    public GlobalExceptionHandler(SlackNotifier slackNotifier) {
        this.slackNotifier = slackNotifier;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneral(Exception e , HttpServletRequest request ){

        String stackTrace = getShortStackTrace(e);
        String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
        String slackMessage = String.format(
                "*Exception Occurred*\n" +
                        "*Type:* %s\n" +
                        "*Message:* %s\n" +
                        "*Endpoint:* %s %s%s\n" +
                        "*Time:* %s\n" +
                        "*Stack Trace:*\n```%s```",
                e.getClass().getName(),
                e.getMessage(),
                request.getMethod(),
                request.getRequestURI(),
                queryString,
                LocalDateTime.now(),
                stackTrace
        );

        slackNotifier.send(slackMessage);

        Map<String ,Object> body = Map.of(
                "Status" , 500 ,
                "error" , "Internal server error",
                "message" , e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException e){
        Map<String, Object> body = Map.of(
                "status", 404,
                "error", "Not Found",
                "message", e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
    private String getShortStackTrace(Exception e) {
        StackTraceElement[] elements = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        int limit = Math.min(10, elements.length);
        for (int i = 0; i < limit; i++) {
            sb.append(elements[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
