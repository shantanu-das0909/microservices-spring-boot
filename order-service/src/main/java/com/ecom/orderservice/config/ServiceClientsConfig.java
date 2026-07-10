package com.ecom.orderservice.config;

import com.ecom.orderservice.constants.InventoryServiceConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class ServiceClientsConfig {

    private final InventoryServiceConstants inventoryServiceConstants;

    @Bean
    public RestClient inventoryRestClient(RestClient.Builder builder){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(inventoryServiceConstants.connectionTimeout());
        factory.setReadTimeout(inventoryServiceConstants.readTimeout());

        return builder
                .requestFactory(factory)
                .baseUrl(inventoryServiceConstants.baseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

}
