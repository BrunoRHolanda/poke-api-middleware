package com.poke.api.middleware.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

/**
 * Main class for bootstrapping the Spring Boot application.
 * This class is the entry point of the application and sets up the default environment profile.
 */
@SpringBootApplication
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * The main method used to start the Spring Boot application.
     * It sets the default profile to "development" and logs the initialization steps.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        LOG.info("[step:to-be-init] [id:1] Initializing Spring");
        System.setProperty(
                AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,
                "development"
        );
        SpringApplication.run(
                Main.class,
                args
        );
        LOG.info("[step:initialized] [id:2] Spring initialized..");
    }
}
