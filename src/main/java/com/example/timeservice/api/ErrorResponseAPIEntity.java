package com.example.timeservice.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * ErrorResponseAPIEntity is a wrapper for errors happened in the method.
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 4/29/23 9:29 AM
 */
@Data
//@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Wrapper around errors happened in the service")
public class ErrorResponseAPIEntity {
    /**
     * Arbitrary object to describe errors happened during execution of the method.
     */
    private List<ErrorAPIEntity> errors;

    public ErrorResponseAPIEntity(List<ErrorAPIEntity> errors) {
        this.errors = errors;
    }

    public ErrorResponseAPIEntity(ErrorAPIEntity ... errors) {
        this.errors = Arrays.stream(errors).toList();
    }

    public static final String EXAMPLE_NOT_AUTHENTICATED="{\"errors\":{"
        + "\"code\": 401,"
        + "\"message\": \"User not authenticated\","
        + "\"timestamp\": \"2023-04-25T17:32:04.366Z\""
        + "}"
        + "}";
    public static final String EXAMPLE_FORBIDDEN="{\"errors\":{"
            + "\"code\": 403,"
            + "\"message\": \"Forbidden access to the resource\","
            + "\"timestamp\": \"2023-04-25T17:32:04.366Z\""
            + "}"
            + "}";
    public static final String EXAMPLE_NOT_FOUND="{\"errors\":{"
            + "\"code\": 404,"
            + "\"message\": \"Resource not found\","
            + "\"timestamp\": \"2023-04-25T17:32:04.366Z\""
            + "}"
            + "}";
    public static final String EXAMPLE_INTERNAL_ERROR="{\"errors\":{"
            + "\"code\": 500,"
            + "\"message\": \"Some internal error\","
            + "\"timestamp\": \"2023-04-25T17:32:04.366Z\""
            + "}"
            + "}";
}
