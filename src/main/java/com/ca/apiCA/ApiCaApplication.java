package com.ca.apiCA;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(
		servers = {@Server(url = "/api/", description = "Default Server URL")},
		info = @Info(title = "apiCA", version = "1", description = "API desenvolvida para o Cento Acadêmico do campus VII da UEPB."))
public class ApiCaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCaApplication.class, args);
	}

}
