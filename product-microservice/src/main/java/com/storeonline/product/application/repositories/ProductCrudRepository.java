package com.storeonline.product.application.repositories;

import com.storeonline.product.domain.models.Product;
import com.storeonline.reactive.repository.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
//PUERTOS
public interface ProductCrudRepository extends ReactiveCrudRepository<Product, Integer> {
    Flux<Product> listByCategoryId(int categoryId);
}
