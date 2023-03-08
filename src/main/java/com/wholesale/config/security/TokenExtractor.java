package com.wholesale.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.nio.file.AccessDeniedException;

/*
* This is sample implementation and not completed because of login out of scope
*/

@Configuration
public class TokenExtractor {

    public static String getUserIdFromHeader(HttpHeaders httpHeaders) throws AccessDeniedException {
        String token = httpHeaders.getFirst("authorization");
        if(token != null && !token.isEmpty()){
            String[] tokenData = token.split(" ");
            if(tokenData.length == 2) {
                return token.split(" ")[1];
            }
        }
        throw new AccessDeniedException("Invalid authorization");
    }
}
