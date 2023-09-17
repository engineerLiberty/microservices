package com.engineerLiberty.Inventoryservice;

import com.engineerLiberty.Inventoryservice.model.Inventory;
import com.engineerLiberty.Inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(8);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13_blue");
			inventory1.setQuantity(10);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_13_white");
			inventory2.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
