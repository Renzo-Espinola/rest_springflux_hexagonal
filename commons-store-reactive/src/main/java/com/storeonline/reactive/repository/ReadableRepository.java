package com.storeonline.reactive.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReadableRepository<M, ID> {
	Mono<M> findById(ID id);
	Flux<M> listAll();
}
