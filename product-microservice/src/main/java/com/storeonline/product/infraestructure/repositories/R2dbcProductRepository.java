package com.storeonline.product.infraestructure.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.storeonline.product.domain.models.Product;
import com.storeonline.product.infraestructure.entities.ProductEntity;

import reactor.core.publisher.Flux;

public interface R2dbcProductRepository extends R2dbcRepository<ProductEntity, Integer> {
	@Query("SELECT * FROM product WHERE category_id=:categoryId")
	Flux<Product> listByCategoryId(int categoryId);
}
