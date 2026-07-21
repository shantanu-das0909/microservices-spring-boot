package com.ecom.orderservice.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public static void stubForInventoryCallCheckInventoryWhenInventoryIsAvailable() {
        stubFor(
                get(urlEqualTo("/api/inventory?skuCode=SAMSUNG_S25_ULTRA&quantity=1"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(Boolean.toString(true))
                        )
        );
    }
}
