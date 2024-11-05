package com.example.timeservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPIConfiguration
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 11/5/24 10:35 AM
 */
@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:9999");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Serge Belolipetski");
        myContact.setEmail("your.email@gmail.com");

        String version = System.getenv("COMPONENT_VERSION");
        version = ((version == null) ? "0.0.1-SNAPSHOT" : version);

        Info information = new Info()
                .title("Sample Java Time Service API")
                .version(version)
                .description("This API exposes endpoints to get version and datetime.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}