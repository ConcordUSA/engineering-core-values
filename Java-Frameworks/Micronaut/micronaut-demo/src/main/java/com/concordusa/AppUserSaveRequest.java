package com.concordusa;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

import javax.validation.constraints.NotNull;

@Introspected
public record AppUserSaveRequest(@NotNull String firstName,
                                 @Nullable String lastName,
                                 @Nullable String email) {
}
