package com.ecom.orderservice.config;

import com.ecom.orderservice.clients.InventoryServiceClient;
import com.ecom.orderservice.constants.InventoryServiceConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class ServiceClientsConfig {

    private final InventoryServiceConstants inventoryServiceConstants;

    @Bean
    public InventoryServiceClient inventoryRestClient(RestClient.Builder builder){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(inventoryServiceConstants.connectionTimeout());
        factory.setReadTimeout(inventoryServiceConstants.readTimeout());

        // 1. define the rest client with its config
        RestClient restClient = builder
                .requestFactory(factory)
                .baseUrl(inventoryServiceConstants.baseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();

        // 2. Adapt the RestClient for HTTP interface support
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);

        // 3. Build the proxy factory
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        // 4. Create and return the proxy of you service client interface
        return proxyFactory.createClient(InventoryServiceClient.class);

    }

}
