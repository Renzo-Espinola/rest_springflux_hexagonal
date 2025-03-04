package com.storeonline.product.infraestructure.mapper;

import com.storeonline.product.domain.models.Product;
import com.storeonline.product.infraestructure.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    Product entityToModel(ProductEntity entity);

    @Mapping(target = "categoryId",
            source = "category.categoryId")
    ProductEntity modelToEntity(Product model);
}
