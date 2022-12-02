package com.concordusa;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

@Repository
public interface AppUserRepository extends ReactorCrudRepository<AppUser, Long> {
}
