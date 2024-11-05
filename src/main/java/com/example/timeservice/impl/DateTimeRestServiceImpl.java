package com.example.timeservice.impl;

import com.example.timeservice.api.DateTime;
import com.example.timeservice.api.DateTimeRestService;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Implementation of DateTimeRestService
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 11/4/24 5:56 PM
 */
@RestController
@Slf4j
public class DateTimeRestServiceImpl implements DateTimeRestService {
    private final Tracer tracer;

    public DateTimeRestServiceImpl(OpenTelemetry openTelemetry) {
        this.tracer = openTelemetry.getTracer("datetime-controller-tracer");
    }

    @Override
    public DateTime getDateTime() {
        log.info("Getting date and time...");
        Span span = tracer.spanBuilder("datetime-span").startSpan();
        span.addEvent("Date and time requested");
        log.debug("Span {} has been created and event added", span.getSpanContext().getSpanId());

        DateTime dt = new DateTime();
        dt.setDateTime("" + new Date(System.currentTimeMillis()));
        String instance = System.getenv("NAME");
        dt.setInstance((instance == null) ? "local" : instance);

        span.setAttribute("datetime", dt.getDateTime());
        log.debug("datetime obtained: {}", dt.getDateTime());

        log.debug("Calling method1");
        otherMethod1();
        log.debug("Calling method3");
        otherMethod3();

        span.end();
        log.debug("Span {} has ended", span.getSpanContext().getSpanId());

        log.info("Date and time: " + dt.getDateTime());

        return dt;
    }

    private void otherMethod1() {
        log.info("Entered otherMethod1");
        Span span = tracer.spanBuilder("other-method1-span").startSpan();
        span.addEvent("Executing other method1");
        // emulating heavy lifting
        long delay = RandomDelay.executeRandomDelay(10, 100);
        span.setAttribute("delay", delay);
        span.addEvent("Calling otherMethod2");
        otherMethod2();
        span.end();
        log.info("Exited otherMethod1 with {} ms delay", delay);
    }

    private void otherMethod2() {
        log.info("Entered otherMethod2");
        Span span = tracer.spanBuilder("other-method2-span").startSpan();
        // emulating heavy lifting
        long delay = RandomDelay.executeRandomDelay(10, 100);
        span.setAttribute("delay", delay);
        span.end();
        log.info("Exited otherMethod2 with {} ms delay", delay);
    }

    private void otherMethod3() {
        log.info("Entered otherMethod3");
        Span span = tracer.spanBuilder("other-method3-span").startSpan();
        // emulating heavy lifting
        long delay = RandomDelay.executeRandomDelay(10, 100);
        span.setAttribute("delay", delay);
        if (delay > 80) {
            throw new RuntimeException("otherMethod3 took too long to respond in time");
        }
        span.end();
        log.info("Exited otherMethod3 with {} ms delay", delay);
    }
}
