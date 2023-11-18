package com.assignment.restapi.restclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Scope("prototype")
public class RestClient<T> {

    @Autowired
    RestTemplate restTemplate;

    @Value("${resource.url}")
    private String resourceUrl;

    public List<T> processRestCall(String url, T t) throws Exception {
        String jsonObjectList = restTemplate.getForObject(resourceUrl + url, String.class);
        return new ObjectMapper().readValue(jsonObjectList, new TypeReference<List<T>>() {});
    }
}
