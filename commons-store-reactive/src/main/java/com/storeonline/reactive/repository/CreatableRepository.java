package com.storeonline.reactive.repository;

import reactor.core.publisher.Mono;

public interface CreatableRepository<M>{
	Mono<M> create(M model);
}
