package com.microservice.product_ms.config;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class FeignClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(FeignClientConfig.class);

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

            if(authenticationToken != null){
                String username = authenticationToken.getPrincipal().toString();

                List<String> rls = authenticationToken.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role.toString()).getAuthority()).collect(Collectors.toList());
                logger.info(String.format("username -> %s, roles -> %s",username, rls.toString()));
                String roles = String.join(",", rls);
                requestTemplate.header("username",username);
                requestTemplate.header("roles",roles);
            }
            else{
                logger.info("header is null");
            }
        };
    }
}
