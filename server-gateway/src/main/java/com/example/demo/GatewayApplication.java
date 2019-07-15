package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(UriConfiguration.class)
@EnableEurekaClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator getRouteLocator(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
//        return builder.routes().
//                route(predicateSpec -> predicateSpec
//                        .path("/get")
//                        .filters(gatewayFilterSpec -> gatewayFilterSpec
//                                .addRequestHeader("hello", "word"))
//                        .uri(uriConfiguration.getHttpBinUri()))
//                .route(predicateSpec -> predicateSpec
//                        .host("*.hystrix.com")
//                        .filters(gatewayFilterSpec -> gatewayFilterSpec
//                                .hystrix(config -> config
//                                        .setName("test")
//                                        .setFallbackUri("forward:/fallback")))
//                        .uri(uriConfiguration.getHttpBinUri()))
//                .build();
//    }

    @Bean
    KeyResolver userKeyResolver() {
        return (exchange ->
            Mono.just(exchange.getRequest().getRemoteAddress().getHostName())
        );
    }

//    @Bean
//    RateLimiter myRateLimiter() {
//        return new RedisRateLimiter(10, 20);
//    }

    @RequestMapping(value = "fallback")
    Mono<String> fallback() {
        return Mono.just("fallback");
    }
}

@ConfigurationProperties(prefix = "ser")
class UriConfiguration {
    @Value("$ser.httpBinUri")
    private String httpBinUri;

    public String getHttpBinUri() {
        return httpBinUri;
    }

    public void setHttpBinUri(String httpBinUri) {
        this.httpBinUri = httpBinUri;
    }
}
