package com.assignment.restapi.configuration;

import com.assignment.restapi.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
public class MainConfiguration implements WebMvcConfigurer {
    private final static int RESTCALL_READ_TIMEOUT = 120000;
    private final static int RESTCALL_CONNECTION_TIMEOUT = 120000;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        setTimeout(restTemplate, RESTCALL_READ_TIMEOUT, RESTCALL_CONNECTION_TIMEOUT);
        return restTemplate;
    }

    private void setTimeout(RestTemplate restTemplate, int readTimeOut, int connectionTimeOut) {
        try {
            restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
            SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
            rf.setReadTimeout(readTimeOut);
            rf.setConnectTimeout(connectionTimeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }
}
