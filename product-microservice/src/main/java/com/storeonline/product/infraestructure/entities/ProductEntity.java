package com.storeonline.product.infraestructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("product")
public record ProductEntity(
        @Id
        @Column("product_id")
        int productId,
        @Column("category_id")
        int categoryId,
        String name,
        @Column("sale_price")
        double salePrice) {

}
