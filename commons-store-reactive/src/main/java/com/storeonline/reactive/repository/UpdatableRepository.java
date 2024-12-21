package com.storeonline.reactive.repository;

import reactor.core.publisher.Mono;

public interface UpdatableRepository<M,ID> {
	Mono<M> update(ID id, M newModel);
}
