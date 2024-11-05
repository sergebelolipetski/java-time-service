package com.example.timeservice.impl;

import com.example.timeservice.api.Version;
import com.example.timeservice.api.VersionRestService;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of VersionRestService
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 10/17/24 11:28 AM
 */
@RestController
@Slf4j
public class VersionRestServiceImpl implements VersionRestService {

    private final OpenTelemetry openTelemetry;
    private final Tracer tracer;

    @Value("${spring.application.name}")
    private String componentName;

    public VersionRestServiceImpl(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
        this.tracer = openTelemetry.getTracer("version-controller-tracer");
    }

    @Override
    public Version getVersion() {
        log.debug("Should print to the Otel Collector");
        Span span = tracer.spanBuilder("version-span").startSpan();
        span.addEvent("Getting version");

        Version versionAPIEntity = new Version();

        String name = componentName;
        versionAPIEntity.setName((name == null) ? "service" : name);

        String instance = System.getenv("NAME");
        versionAPIEntity.setInstance((instance == null) ? "local" : instance);

        String version = System.getenv("COMPONENT_VERSION");
        versionAPIEntity.setVersion((version == null) ? "0.0.1-SNAPSHOT" : version);

        span.setAttribute("version", versionAPIEntity.getVersion());
        span.end();

        return versionAPIEntity;
    }
}
