package com.fetch_rewards.receipt_processor.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Abhishek Baidyaroy");
        myContact.setEmail("abhishek.baidyaroy46@gmail.com");

        Info information = new Info()
                .title("receipt-processor")
                .version("1.0.1")
                .description("This API exposes endpoints to receipt-processor.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
