package com.storeonline.product.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.storeonline.product.application.repositories.ProductCategoryCrudRepository;
import com.storeonline.product.domain.models.ProductCategory;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryReactiveCrudServiceTest {
	@Mock
	ProductCategoryCrudRepository categoryRepository;
	
	@InjectMocks
	ProductCategoryReactiveCrudService categoryService;
	
	@Test
	void testCreate() {
		
		 var request = ProductCategory.builder()
	                .enabled(true)
	                .name("Category 2")
	                .categoryId(2)
	                .build();

	        var category = ProductCategory.builder()
	                .enabled(true)
	                .name("Category 2")
	                .categoryId(2)
	                .build();

	        when(categoryRepository.create(request)).thenReturn(Mono.just(category));

	        categoryService.create(request).blockOptional().ifPresent(newCategory -> {
	            assertEquals(category.getCategoryId(), newCategory.getCategoryId());
	        });
	}

}
