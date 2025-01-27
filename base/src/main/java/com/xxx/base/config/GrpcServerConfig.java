package com.xxx.base.config;

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {
    @Bean
    @GrpcGlobalServerInterceptor
    public SeataServerInterceptor seataServerInterceptor() {
        return new SeataServerInterceptor();
    }
}
