package com.storeonline.reactive.service;

import com.storeonline.reactive.repository.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public abstract class ReactiveCrudService<M, ID>{
	
	private final ReactiveCrudRepository<M,ID> repository;

	public ReactiveCrudService(ReactiveCrudRepository<M, ID> repository) {
		this.repository = repository;
	}

	public Mono<M> create(M model){
		return repository.create(model);
	}
	
	public Mono<M> findById(ID id){
		return repository.findById(id);
	}
	
	public Flux<M> listAll(){
		return repository.listAll();
	}
	
	public Mono<Void> delete(ID id){
		return repository.delete(id);
	}
	
	public Mono<M> update(ID id, M newModel){
		return repository.update(id, newModel);
	}
	
	protected ReactiveCrudRepository<M, ID> getRepository(){
		return repository;
	}
}
