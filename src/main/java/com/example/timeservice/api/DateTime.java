package com.example.timeservice.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Date and Time in a string format")
public class DateTime {
    @Schema(description = "Date and Time in a string format",
            example = "Tue Nov 05 14:47:18 UTC 2024")
    private String dateTime;

    @Schema(description = "Name of the instance of this component. " +
            "This normally contains a name of a pod, where the container is running in Kubernetes cluster",
            example = "java-time-service-xk5pj")
    private String instance;
}
