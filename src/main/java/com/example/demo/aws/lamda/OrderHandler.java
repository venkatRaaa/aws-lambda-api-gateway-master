package com.example.demo.aws.lamda;

import java.util.List;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.example.demo.aws.lamda.domain.Order;


public class OrderHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, List<Order>>{

}
