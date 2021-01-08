package com.example.demo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.example.demo.aws.lamda.domain.Order;
import com.example.demo.aws.lamda.repository.OrderDao;

@SpringBootApplication
public class AwsLambdaApiGatewayMasterApplication {

	@Autowired
	private OrderDao orderDao;

	@Bean
	public Supplier<List<Order>> orders() {
		return () -> orderDao.buildOrders();
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent, List<Order>> findOrderByName() {
		return (requestEvent) -> orderDao
				.buildOrders()
				.stream()
				.filter(order -> order.getName().equals(
						requestEvent.getQueryStringParameters()
								.get("orderName")))
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		SpringApplication.run(AwsLambdaApiGatewayMasterApplication.class, args);
	}

}
