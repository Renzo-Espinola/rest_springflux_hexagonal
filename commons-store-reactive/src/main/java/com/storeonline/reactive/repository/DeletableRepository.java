package com.storeonline.reactive.repository;

import reactor.core.publisher.Mono;

public interface DeletableRepository<ID> {
	Mono<Void> delete(ID id);
}
