package com.luizcarmo.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("/auth")
    public ResponseEntity<Map<String, Object>> authFallback(ServerHttpRequest request) {

        Map<String, Object> response = Map.of(
                "service", "AUTH-SERVICE",
                "status", "DOWN",
                "path", request.getURI().getPath(),
                "message", "Authentication service temporarily unavailable",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }
}