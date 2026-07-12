package com.ecom.orderservice;

import com.ecom.orderservice.config.TestContainerConfiguration;
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
