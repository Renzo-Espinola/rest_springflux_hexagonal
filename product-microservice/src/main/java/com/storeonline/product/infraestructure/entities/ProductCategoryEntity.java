package com.storeonline.product.infraestructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("product_category")
public record ProductCategoryEntity(@Id @Column("category_id") int categoryId,
		String name, boolean enabled) {

}
