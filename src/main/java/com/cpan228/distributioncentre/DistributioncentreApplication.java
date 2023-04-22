package com.cpan228.distributioncentre;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cpan228.distributioncentre.model.Item;
import com.cpan228.distributioncentre.model.Item.Brand;
import com.cpan228.distributioncentre.repository.ItemRepository;

@SpringBootApplication
public class DistributioncentreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributioncentreApplication.class, args);
	}
	@Bean
	public CommandLineRunner dataLoader(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder().name("T-Shirt").brand(Brand.BALENCIAGA).yearOfCreation(2023).price(1000.00).quantity(11).build());

			repository.save(Item.builder().name("Sweater").brand(Brand.DIOR).yearOfCreation(2023).price(2000.00).quantity(13).build());

			repository.save(Item.builder().name("Pants").brand(Brand.STONE_ISLAND).yearOfCreation(2023).price(1500.00).quantity(15).build());
		};
	}
}
