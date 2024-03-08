package com.ecommerce.delivery.service.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.delivery.domain.Delivery;
import com.ecommerce.delivery.repository.IDeliveryRepository;
import com.ecommerce.delivery.service.IDeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService implements IDeliveryService {

	@Autowired
	private IDeliveryRepository deliveryRepository;
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Value("${exchange.name}")
    private String exchange;

    @Value("${routing.key}")
    private String routingKey;
    
	@Override
	public Map<String, Object> convertToObject(String jsonS) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(jsonS, new TypeReference<Map<String,Object>>(){});
			
			return map;
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;		
		}
	}
	
	@Override
	public void sendOrderToTransport(String order) {
		Map<String, Object> map = convertToObject(order);		
		String orderId = map.get("order_id").toString();		
		
		log.info(MessageFormat.format("Pedido nº {0} foi enviado para a transportadora Pega Tudo", orderId));
		
	}
	
	@Override
	public void ConfirmationOrder(String confirmation) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Delivery resultDelivery = mapper.readValue(confirmation, Delivery.class);
			resultDelivery.setConfirmationDate(LocalDateTime.now());
			
			deliveryRepository.save(resultDelivery);

			webClient.get()
		    	.uri("order/confirmation")
		    	.retrieve();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void sendMessageBroker(String order) {
		rabbitTemplate.convertAndSend(exchange, routingKey, order);
	}

}
