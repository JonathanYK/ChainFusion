package com.jonathanyk.chainfusion;

import com.fasterxml.jackson.databind.JsonNode;
import com.jonathanyk.chainfusion.dto.ProductRequest;
import com.jonathanyk.chainfusion.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ChainfusionApplicationTests {

	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.13")
			.waitingFor(Wait.forListeningPort());

	static {
		mongoDBContainer.start();
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void createProductTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON.getMediaType())
				.content(objectMapper.writeValueAsString(getSampleProductRequest())))
				.andExpect(status().isCreated());
	}

	@Test
	void getProductTest() throws Exception {
		createProductTest();
		String responseContent = mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
				.andReturn().getResponse().getContentAsString();
		JsonNode responseJson = objectMapper.readTree(responseContent);

		ProductRequest productRequest = getSampleProductRequest();
		Assertions.assertEquals(productRequest.getName(), responseJson.get(0).get("name").asText());
		Assertions.assertEquals(productRequest.getPrice().toString(), responseJson.get(0).get("price").asText());
		Assertions.assertEquals(productRequest.getDescription(), responseJson.get(0).get("description").asText());
		log.info("product {} saved and fetched successfully", productRequest);
	}

	private ProductRequest getSampleProductRequest() {
		return ProductRequest.builder()
				.name("iPhone X")
				.description("new with tags")
				.price(BigDecimal.valueOf(100))
				.build();

	}

}
