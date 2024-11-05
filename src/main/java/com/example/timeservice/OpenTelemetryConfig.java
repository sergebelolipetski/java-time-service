package com.example.timeservice;

import io.opentelemetry.api.OpenTelemetry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * OpenTelemetryConfig.
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 6/21/24
 */
@Slf4j
@Configuration
// https://github.com/open-telemetry/opentelemetry-java-instrumentation/blob/main/instrumentation/spring/spring-boot-autoconfigure/src/main/java/io/opentelemetry/instrumentation/spring/autoconfigure/OpenTelemetryAutoConfiguration.java
@ComponentScan({
    "io.opentelemetry.instrumentation.spring.autoconfigure"
})
public class OpenTelemetryConfig {

    private final OpenTelemetry openTelemetry;

    OpenTelemetryConfig(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
        log.debug("OpenTelemetry: {}", openTelemetry.toString());
    }
}
