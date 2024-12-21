package com.storeonline.product.infraestructure.adapter;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.storeonline.product.application.repositories.ProductCrudRepository;
import com.storeonline.product.domain.models.Product;
import com.storeonline.product.domain.models.ProductCategory;
import com.storeonline.product.infraestructure.entities.ProductEntity;
import com.storeonline.product.infraestructure.mapper.ProductCategoryMapper;
import com.storeonline.product.infraestructure.mapper.ProductMapper;
import com.storeonline.product.infraestructure.repositories.R2dbcProductCategoryRepository;
import com.storeonline.product.infraestructure.repositories.R2dbcProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class R2dbcProductAdapter implements ProductCrudRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(R2dbcProductAdapter.class);

    private final R2dbcProductRepository repository;
    private final R2dbcProductCategoryRepository categoryRepository;

    private final ProductMapper mapper;
    private final ProductCategoryMapper categoryMapper;

    public R2dbcProductAdapter(R2dbcProductRepository repository, R2dbcProductCategoryRepository categoryRepository,
            ProductMapper mapper, ProductCategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Flux<Product> listByCategoryId(int categoryId) {
        return repository.listByCategoryId(categoryId);
    }

    @Override
    public Mono<Product> create(Product model) {
        var entity = mapper.modelToEntity(model);
        return repository.save(entity)
                .flatMap(this::mapping);
    }

    private Mono<Product> mapping(ProductEntity productEntity) {
        var product = mapper.entityToModel(productEntity);
        if (productEntity.categoryId() == 0) {
            return Mono.just(product);
        }
        return categoryRepository.findById(productEntity.categoryId())
                .map(category -> {
                    var categoryEntity = categoryMapper.entityToModel(category);
                    LOGGER.debug("Category found: {}", categoryEntity);
                    product.setCategory(categoryEntity);
                    return product;
                });
    }

    @Override
    public Mono<Product> findById(Integer integer) {
        var entity = repository.findById(integer);
        return entity.map(mapper::entityToModel);
    }

    @Override
    public Flux<Product> listAll() {
        return repository.findAll().map(mapper::entityToModel);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Product> update(Integer id, Product newModel) {
        return repository.findById(id)
                .flatMap(found -> {
                    var categoryId = Optional.ofNullable(newModel.getCategory())
                            .map(ProductCategory::getCategoryId)
                            .orElse(0);
                    var newDate = new ProductEntity(id, categoryId, newModel.getName(),
                            newModel.getSalePrice());
                    return repository.save(newDate).map(mapper::entityToModel);
                }).switchIfEmpty(Mono.empty());
    }

}
