package com.concordusa;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

import javax.validation.constraints.NotNull;

@Introspected
public record AppUserUpdateRequest(@NotNull Long id,
                                   @Nullable String firstName,
                                   @Nullable String lastName,
                                   @Nullable String email) {
}
