package com.jonathanyk.InventoryService;

import com.jonathanyk.InventoryService.model.Inventory;
import com.jonathanyk.InventoryService.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

@SpringBootApplication(scanBasePackages = {"com.jonathanyk.InventoryService", "com.jonathanyk.chainCommons.exception"})
@Slf4j
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	// init db data
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {

			try {
				String inventory1SkuCode = "iPhone_15_Pro_Max";
				if (inventoryRepository.findBySkuCode(inventory1SkuCode).isEmpty()) {
					Inventory inventory1 = new Inventory();
					inventory1.setSkuCode(inventory1SkuCode);
					inventory1.setQuantity(10);

					inventoryRepository.save(inventory1);
					log.info("inventory 1 saved. sku code: " + inventory1SkuCode);
				}

				String inventory2SkuCode = "iPad_5_Pro";
				if(inventoryRepository.findBySkuCode(inventory2SkuCode).isEmpty()) {
					Inventory inventory2 = new Inventory();
					inventory2.setSkuCode(inventory2SkuCode);
					inventory2.setQuantity(0);

					inventoryRepository.save(inventory2);
					log.info("inventory 2 saved. sku code: " + inventory2SkuCode);
				}

			} catch (IncorrectResultSizeDataAccessException ex) {
				log.error("Failed to initiate inventory params: " + ex.getMessage());
				System.exit(1);
			} catch (Exception ex) {
				log.error("Initiation inventory failed: " + ex.getMessage());
				System.exit(1);
			}

		};
	}
}
