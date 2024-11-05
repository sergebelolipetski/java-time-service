package com.example.timeservice.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * ErrorAPIEntity.
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 4/24/23 10:10 PM
 */
@Data
@Schema(description = "Information about the error")
public class ErrorAPIEntity {
    @Schema(description = "HTTP status of the error.",
            example = "500",
            accessMode = Schema.AccessMode.READ_ONLY)
    private int code;

    @Schema(description = "Message about the error.",
            example = "Something went wrong.",
            accessMode = Schema.AccessMode.READ_ONLY)
    private String message;

    @Schema(description = "Date of the error.",
            example = "2023-04-25T17:32:04.366Z",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date timestamp = new Date();

    public ErrorAPIEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
