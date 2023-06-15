package com.telstra.phoneservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = CustomerPhoneServiceApplication.class)
@ActiveProfiles("test")
class CustomerPhoneServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
