package com.jonathanyk.chainfusion;

<<<<<<< Updated upstream
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChainfusionApplicationTests {

	@Test
	void contextLoads() {
=======
import com.jonathanyk.chainfusion.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void createProductTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON.getMediaType())
				.content(objectMapper.writeValueAsString(getSamoleProductRequest())))
				.andExpect(status().isCreated());
	}

	private ProductRequest getSamoleProductRequest() {
		return ProductRequest.builder()
				.name("iPhone X")
				.description("new with tags")
				.price(BigDecimal.valueOf(100))
				.build();
>>>>>>> Stashed changes
	}

}
