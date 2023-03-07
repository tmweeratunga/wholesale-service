package com.wholesale.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.nio.file.AccessDeniedException;

@Configuration
public class TokenExtractor {

    public static String getUserIdFromHeader(HttpHeaders httpHeaders) throws AccessDeniedException {
        String token = httpHeaders.getFirst("authorization");
        if(token == null || token.isEmpty()){
            throw new AccessDeniedException("Invalid authorization");
        }
        return token.split(" ")[1];
    }
}
