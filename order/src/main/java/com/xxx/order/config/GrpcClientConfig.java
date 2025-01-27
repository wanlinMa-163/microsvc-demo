package com.xxx.order.config;

import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    @GrpcGlobalClientInterceptor
    public SeataClientInterceptor seataClientInterceptor() {
        return new SeataClientInterceptor();
    }
}
