package com.devsu.hackerearth.backend.account.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.devsu.hackerearth.backend.account.client.ClientResponse;

@Component
public class ClientRest {

    @Value("${external.service.url}")
    private String serviceUrl;

    private static final RestTemplate restTemplate = new RestTemplate();


    public Optional<ClientResponse> findClientById(Long id){
        try {
            String url = serviceUrl+"/api/clients/"+id;
            ResponseEntity<ClientResponse> clientResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                ClientResponse.class
            );
            return Optional.ofNullable(clientResponse.getBody());

        } catch (Exception e) {
           return Optional.empty();
        }
    }
    
}
