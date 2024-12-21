package com.storeonline.product.application.services;

import org.springframework.stereotype.Service;

import com.storeonline.product.application.repositories.ProductCrudRepository;
import com.storeonline.product.domain.models.Product;
import com.storeonline.reactive.service.ReactiveCrudService;

import reactor.core.publisher.Flux;
@Service
public class ProductReactiveCrudService extends ReactiveCrudService<Product, Integer> {

    public ProductReactiveCrudService(ProductCrudRepository repository) {
        super(repository);
    }


    public Flux<Product> listByCategoryId(int id) {
        return getRepository().listByCategoryId(id);
    }

    @Override
    protected ProductCrudRepository getRepository() {
        return (ProductCrudRepository) super.getRepository();
    }
}
