package com.mg.microservices.api_gateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 8/11/2025
 * Time: 5:51 PM
 */
@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //customize the routes

                .route(p -> p.path("/get")
                        .filters(
                                f -> f.addRequestHeader("MyHeader", "MyURI")
                                        .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))

                .route(p ->
                        p.path("/api/v1/currency-exchange/**")
                        .uri("lb://CURRENCY-EXCHANGE")
                )
                .route(p ->
                        p.path("/api/v1/currency-conversion/**")
                                .uri("lb://CURRENCY-CONVERSION")
                )
                .route(p ->
                        p.path("/api/v1/currency-conversion-feign/**")
                                .uri("lb://CURRENCY-CONVERSION")
                )

                .build();
    }

}
