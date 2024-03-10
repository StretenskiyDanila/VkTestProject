package com.example.vktestproject.services.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class RestTemplateWork {

    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public <T> T getObject(String URI, Map<String, String> params, Class<T> returnClass) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URI);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        return restTemplate.getForObject(builder.toUriString(), returnClass);
    }

    public <T> T getObject(String URI, Class<T> returnClass) {
        return restTemplate.getForObject(URI, returnClass);
    }

    public <T> T postForObject(String URI, Object request, Class<T> returnClass) {
        return restTemplate.postForObject(URI, request, returnClass);
    }

    public <T> ResponseEntity<T> exchange(String URI, HttpMethod method, HttpEntity<?> entity, Class<T> returnClass) {
        return restTemplate.exchange(URI, method, entity, returnClass);
    }

    public <T> T patchObject(String URI, Object request, Class<T> returnClass) {
        return restTemplate.patchForObject(URI, request, returnClass);
    }

}
