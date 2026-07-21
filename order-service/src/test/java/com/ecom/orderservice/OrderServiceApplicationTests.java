package com.ecom.orderservice;

import com.ecom.orderservice.config.TestContainerConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestContainerConfiguration.class)
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
