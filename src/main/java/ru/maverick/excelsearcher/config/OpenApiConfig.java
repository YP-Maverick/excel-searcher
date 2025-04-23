package ru.maverick.excelsearcher.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpenAPI specification excel-searcher")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Egor Ivanov")
                                .email("Egor.Ivanov.v@yandex.ru")
                        )
                )
                .servers(List.of(
                        new Server()
                                .description("Local ENV")
                                .url("http//:localhost:8080")
                        )
                );
    }
}
