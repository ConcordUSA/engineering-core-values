package com.concordusa;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;

import static io.micronaut.context.env.Environment.TEST;

@Singleton
@Requires(notEnv = TEST)
public class Dataloader {

    private static final String SCHEMA_LOCATION = "db_migrations";

    private final String url;
    private final String username;
    private final String password;

    Dataloader(@Property(name = "datasources.default.url") String url,
               @Property(name = "datasources.default.username") String username,
               @Property(name = "datasources.default.password") String password) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @EventListener
    @Async
    void initiateMigration(final StartupEvent startupEvent) {
        Flyway.configure()
                .locations(Location.FILESYSTEM_PREFIX + SCHEMA_LOCATION)
                .dataSource(url, username, password)
                .load()
                .migrate();
    }

}
