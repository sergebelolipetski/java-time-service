package com.example.timeservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

// https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Version", description = "Name of the component and its version")
public interface VersionRestService {
    @RequestMapping(
            value = "/v1/version",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get API version",
            description = "Get API version in a human-readable format.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operation successful", content = @Content(schema = @Schema(implementation = Version.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorResponseAPIEntity.class), examples = @ExampleObject(ErrorResponseAPIEntity.EXAMPLE_FORBIDDEN))),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponseAPIEntity.class), examples = @ExampleObject(ErrorResponseAPIEntity.EXAMPLE_NOT_FOUND))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseAPIEntity.class), examples = @ExampleObject(ErrorResponseAPIEntity.EXAMPLE_INTERNAL_ERROR)))
            }
    )
    Version getVersion();
}
