package com.shopsphere.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class RateLimitingFilter implements GlobalFilter {

    private static final int maxReateLimit= 5;
    private ReactiveRedisTemplate<String, Integer> redisTemplate;

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ipAddress;
        ipAddress = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if(ipAddress == null){
            ipAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        }
        String key = "rate-limit" + ipAddress;
        return redisTemplate.opsForValue()
                .increment(key)
                .flatMap( count ->{
                        if (count ==1){
                            redisTemplate.expire(key, Duration.ofSeconds(1)).subscribe();
                        }
                        if (count>maxReateLimit){
                            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                            return exchange.getResponse().setComplete();
                        }
                        return chain.filter(exchange);
                    }

                );
    }

    public int getOrder(){
        return -100;
    }
}
