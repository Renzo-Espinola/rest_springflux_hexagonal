package com.storeonline.product.infraestructure.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.storeonline.product.infraestructure.entities.ProductCategoryEntity;

public interface R2dbcProductCategoryRepository extends R2dbcRepository<ProductCategoryEntity, Integer>{

}
