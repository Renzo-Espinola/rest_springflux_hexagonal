package com.storeonline.reactive.repository;

public interface ReactiveCrudRepository<M,ID> extends 
CreatableRepository<M>, 
DeletableRepository<ID>,
ReadableRepository<M,ID>,
UpdatableRepository<M,ID>{
}
