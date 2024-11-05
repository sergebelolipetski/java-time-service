package com.example.timeservice.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data about version")
public class Version {
    @Schema(description = "Name of the component",
            example = "java-time-service")
    private String name;

    @Schema(description = "Name of the instance of this component. " +
            "This normally contains a name of the pod, where the container is running in the cluster",
            example = "java-time-service-xk5pj")
    private String instance;

    @Schema(description = "Version signature",
            example = "0.0.1-SNAPSHOT")
    private String version;
}
