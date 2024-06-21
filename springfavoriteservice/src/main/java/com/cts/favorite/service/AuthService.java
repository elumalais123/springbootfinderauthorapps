package com.cts.favorite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cts.favorite.controller.AuthenticationClient;
import com.cts.favorite.exceptions.ServerConnectionException;
import com.cts.favorite.exceptions.UnAuthorizedException;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Slf4j
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    AuthenticationClient authenticationClient;

    public Map<String, String> validateToken(String token) {

        try {
            ResponseEntity<Map<String, String>> response = authenticationClient.validateToken(token);

            log.info(response.getBody()+"from*********");
            log.info(response.getBody()+"from*********");
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new UnAuthorizedException("Failed to validate token: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new ServerConnectionException("connection refused: " + e.getMessage());
        }
    }

}