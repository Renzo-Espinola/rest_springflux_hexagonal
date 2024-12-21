package com.storeonline.product.application.services;

import org.springframework.stereotype.Service;
import com.storeonline.product.application.repositories.ProductCategoryCrudRepository;
import com.storeonline.product.domain.models.ProductCategory;
import com.storeonline.reactive.service.ReactiveCrudService;
import reactor.core.publisher.Mono;

@Service
public class ProductCategoryReactiveCrudService extends ReactiveCrudService<ProductCategory,Integer> {

	public ProductCategoryReactiveCrudService(ProductCategoryCrudRepository repository) {
		super(repository);
	}
	
	@Override
	protected ProductCategoryCrudRepository getRepository() {
		return (ProductCategoryCrudRepository) super.getRepository();
	}
	
	public Mono<ProductCategory> updateEnabled(int categoryId, boolean enabled){
		return getRepository().updateEnabled(categoryId, enabled);
	}
}
