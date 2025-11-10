package com.apigateway;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class APIGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(APIGatewayApplication.class, args);
	}
}