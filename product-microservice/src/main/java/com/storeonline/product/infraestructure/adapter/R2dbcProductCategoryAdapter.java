package com.storeonline.product.infraestructure.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.storeonline.product.application.repositories.ProductCategoryCrudRepository;
import com.storeonline.product.domain.models.ProductCategory;
import com.storeonline.product.infraestructure.entities.ProductCategoryEntity;
import com.storeonline.product.infraestructure.mapper.ProductCategoryMapper;
import com.storeonline.product.infraestructure.repositories.R2dbcProductCategoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class R2dbcProductCategoryAdapter implements ProductCategoryCrudRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(R2dbcProductCategoryAdapter.class);
	private final R2dbcProductCategoryRepository repository;
	private final ProductCategoryMapper mapper;
	
	public R2dbcProductCategoryAdapter(R2dbcProductCategoryRepository repository, ProductCategoryMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Mono<ProductCategory> create(ProductCategory model) {
		var entity = mapper.modelToEntity(model);
		var created = repository.save(entity);
		return created.map(mapper::entityToModel);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return repository
				.findById(id)
				.flatMap(found-> repository.delete(found))
				.switchIfEmpty(Mono.empty());
	}

	@Override
	public Mono<ProductCategory> findById(Integer id) {
		var entity = repository.findById(id);
		return entity.map(mapper::entityToModel);
	}

	@Override
	public Flux<ProductCategory> listAll() {
		return repository.findAll().map(mapper::entityToModel);
	}

	@Override
	public Mono<ProductCategory> update(Integer id, ProductCategory newModel) {
		return repository
				.findById(id)
				.flatMap(found->{
					var newData = new ProductCategoryEntity(id, newModel.getName(), newModel.isEnabled());
					return repository.save(newData).map(mapper::entityToModel);
				}).switchIfEmpty(Mono.empty());
	}

	@Override
	public Mono<ProductCategory> updateEnabled(int categoryId, boolean enabled) {
		return repository
				.findById(categoryId)
				.flatMap(found->{
					var newData = new ProductCategoryEntity(found.categoryId(), found.name(), enabled);
					return repository.save(newData).map(mapper::entityToModel);
				}).switchIfEmpty(Mono.empty());
	}
}
