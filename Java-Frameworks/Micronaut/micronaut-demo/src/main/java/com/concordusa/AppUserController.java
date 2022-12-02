package com.concordusa;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.reactivestreams.Publisher;

import javax.validation.Valid;
import java.net.URI;

@Controller("/users")
@AllArgsConstructor
@Slf4j
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Publisher<AppUser> getById(@PathVariable(name = "id") Long id) {
        log.info("Retrieving user with ID: " + id);
        return appUserRepository.findById(id);
    }

    @Post
    @Status(HttpStatus.CREATED)
    Publisher<HttpResponse<AppUser>> save(@Valid @Body AppUserSaveRequest command) {
        val savedEntity = AppUser.builder()
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .build();
        return appUserRepository.save(savedEntity)
                .map(appUser -> HttpResponse
                        .<AppUser>noContent()
                        .header(HttpHeaders.LOCATION, URI.create("/users/" + appUser.getId()).getPath()));
    }

    @Put
    @Status(HttpStatus.NO_CONTENT)
    Publisher<HttpResponse<AppUser>> update(@Valid @Body AppUserUpdateRequest command) {
        val updatedEntity = AppUser.builder()
                .id(command.id())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .build();
        return appUserRepository.update(updatedEntity)
                .map(appUser -> HttpResponse
                        .<AppUser>noContent()
                        .header(HttpHeaders.LOCATION, URI.create("/users/" + appUser.getId()).getPath()));
    }

    @Delete("/{id}")
    Publisher<HttpResponse<?>> deleteById(@PathVariable Long id) {
        return appUserRepository.deleteById(id)
                .map(deleteId -> HttpResponse.noContent());
    }
}
