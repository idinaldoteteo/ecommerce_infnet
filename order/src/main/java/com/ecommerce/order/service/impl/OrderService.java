package com.ecommerce.order.service.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.order.domain.DeliveryConfirmation;
import com.ecommerce.order.domain.NotificationMessage;
import com.ecommerce.order.domain.Order;
import com.ecommerce.order.domain.PaymentConfirmation;
import com.ecommerce.order.repository.IOrderRepository;
import com.ecommerce.order.service.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService extends GenericService<Order, Long, IOrderRepository> implements IOrderService {

	private IOrderRepository orderRepository;
	
	public OrderService(IOrderRepository repository) {
		super(repository);
		orderRepository = repository;
	}
	
	@Value("${delivery.exchange.name}")
	private String deliveryExchange;

	@Value("${delivery.routing.key}")
	private String deliverRoutingKey;

	@Value("${notification.exchange.name}")
	private String notificationExchange;

	@Value("${notification.routing.key}")
	private String notificationRoutingKey;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public Map<String, Object> convertToObject(String jsonS) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(jsonS, Map.class);
			
			return map;
			
		} catch (JsonProcessingException e) {
			return null;		
		}
	}
	
	@Override
	public void createOrder(Order order) {
		
		orderRepository.save(order);
		
		//Enivar para Payment Integration
		log.info(MessageFormat.format("[EXTERNO] Enviando mensagem do pedido {0} para o Payment Integration confirmar o pagamento.", 100));
	}

	@Override
	public void confirmationPayment(String confirmation) {
				
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			PaymentConfirmation payment = mapper.readValue(confirmation, PaymentConfirmation.class);

			//Atualizar a order com a aprovação.
			Order order = orderRepository.getReferenceById(payment.getOrderId());			
			order.setAprovetAt(LocalDateTime.now());
			order.setStatus(2);
			
			orderRepository.save(order);
			
			//Enviar mensagem para notificar o cliente
			String msgBody = "Prezado Cliente, seu pedido foi aprovado e está em fase de entrega";
			String msgNotification = ConvertToMsgNotification(order, msgBody);			
			rabbitTemplate.convertAndSend(notificationExchange, notificationRoutingKey, msgNotification);
			
			//Enviar order para fila Delivery para solicitar Transporte
			log.info(MessageFormat.format("[EXTERNO] Enviando mensagem do pedido {0} para solicitar transporte.", order.getId()));
			rabbitTemplate.convertAndSend(deliveryExchange, deliverRoutingKey, msgNotification);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String ConvertToMsgNotification(Order order, String msgBody) throws JsonProcessingException {
		 NotificationMessage msg = new NotificationMessage();		
		 msg.setOrder_id(order.getId());
		 msg.setOrder_status(1);
		 msg.setUser_id(order.getUser_id());		
		 msg.setSubject(msgBody);
		 
		 ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper 
           .writerWithDefaultPrettyPrinter() 
           .writeValueAsString(msg); 
        
        return jsonString;
	}

	@Override
	public void confirmationDelivery(String confirmation) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			DeliveryConfirmation deliveryConfirmation = mapper.readValue(confirmation, DeliveryConfirmation.class);
			
			//Atualizar a order com a confirmação da transportador.
			Order order = orderRepository.getReferenceById(deliveryConfirmation.getOrderId());			
			order.setConfirmationTransportAt(LocalDateTime.now());
			order.setTransportName(deliveryConfirmation.getTransportName());
			order.setStatus(3);
			
			orderRepository.save(order);
			
			//Enviar mensagem para notificar o cliente
			String msgBody = "Prezado Cliente, seu pedido está em rota de entrega";
			String msgNotification = ConvertToMsgNotification(order, msgBody);			
			rabbitTemplate.convertAndSend(notificationExchange, notificationRoutingKey, msgNotification);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

	
