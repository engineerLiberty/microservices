package com.engineerLiberty;

import com.engineerLiberty.request.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Autowired
	private  MockMvc mockMvc;
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}
    @Test
    void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = productRequestData();
		String productString = convertToString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productString))
				.andExpect(MockMvcResultMatchers.status().isCreated());
    }

	private ProductRequest productRequestData() {
		return ProductRequest.builder()
				.name("Television")
				.description("SonyTv")
				.price(BigDecimal.valueOf(300))
				.build();
	}

	private String convertToString(ProductRequest p) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return  objectMapper.writeValueAsString(p);
	}

}
