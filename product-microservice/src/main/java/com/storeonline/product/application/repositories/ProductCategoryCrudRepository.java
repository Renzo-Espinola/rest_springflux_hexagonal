package com.storeonline.product.application.repositories;

import com.storeonline.product.domain.models.ProductCategory;
import com.storeonline.reactive.repository.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
//PUERTOS
public interface ProductCategoryCrudRepository extends ReactiveCrudRepository<ProductCategory, Integer>{
	Mono<ProductCategory> updateEnabled(int categoryId, boolean enabled);
}
