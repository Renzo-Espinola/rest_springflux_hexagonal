package com.storeonline.product.infraestructure.mapper;

import org.mapstruct.Mapper;

import com.storeonline.product.domain.models.ProductCategory;
import com.storeonline.product.infraestructure.entities.ProductCategoryEntity;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {	
	ProductCategory entityToModel(ProductCategoryEntity entity);
	ProductCategoryEntity modelToEntity(ProductCategory model);
}
