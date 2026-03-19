package com.example.sorl.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return  new OpenAPI()
                .info(new Info()
                        .title("API de Busca Inteligente com Persistência ")
                        .description("API de busca aproximada utilizando o Apache Solr," +
                                " com persistência de dados no PostgreSQL.")
                        .version("1.0"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Ambiente de Desenvolvimento (Local)")
                ));

    }
}
