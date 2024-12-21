package com.storeonline.product.application.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.storeonline.product.domain.models.ProductCategory;

@SpringBootTest
@ActiveProfiles(value = "h2")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCategoryCrudRepositoryTest {
	@Autowired
	ProductCategoryCrudRepository productCategoryCrudRepository;
	@Autowired
	ProductCrudRepository productCrudRepository;
	
	@Test
	@Order(1)
	void createCategory() {
		var model = ProductCategory.builder()
					.name("Category 1")
					.enabled(true)
					.build();
		
		productCategoryCrudRepository.create(model)
        .subscribe(category -> assertTrue(category.getCategoryId() > 0));		
	}
}
