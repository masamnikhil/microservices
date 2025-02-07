package com.api.gateway.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;


        public CustomFilter(RouteValidator routeValidator, JwtUtil jwtUtil) {
            super(Config.class);
        }

         static class Config {
             // Add configuration properties if necessary
         }

        @Override
        public GatewayFilter apply(Config config) {
            return (exchange, chain) -> {
                ServerHttpRequest request = exchange.getRequest();

                if(routeValidator.isSecured.test(request)){
                    if(!request.getHeaders().containsKey("Authorization")){
                        return this.Error(exchange, "unAuthorized User", HttpStatus.UNAUTHORIZED);
                    }
                    final String tkn = request.getHeaders().getOrEmpty("Authorization").get(0);

                    if(tkn == null || !tkn.startsWith("Bearer ")){
                        return this.Error(exchange, "unAuthorized Access", HttpStatus.UNAUTHORIZED);
                    }
                    final String token = tkn.substring(7);
                    if(!jwtUtil.isTokenInvalid(token)){
                        return this.Error(exchange, "Session expired", HttpStatus.UNAUTHORIZED);
                    }
                    //this.populateRequestWithHeaders(exchange, token);
                    Claims claims = jwtUtil.extractAllClaims(token);
                    String roles = String.join(",", claims.get("roles", List.class));
                    ServerHttpRequest mutatedReq =  exchange.getRequest().mutate()
                            .header("username", claims.getSubject())
                            .header("roles",roles)
                            .build();
                    return chain.filter(exchange.mutate().request(mutatedReq).build());
                }
                return chain.filter(exchange);


            };
        }

    private Mono<Void> Error(ServerWebExchange exchange, String message, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes());

       return response.writeWith(Mono.just(buffer));
    }


    }

