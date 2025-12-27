package com.shopsphere.gateway.controller;

import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class FallbackController {

    @GetMapping("/fallback/product")
    public ResponseEntity<String> productFallback(){

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Product service is unavailable at this moment");
    }

}
