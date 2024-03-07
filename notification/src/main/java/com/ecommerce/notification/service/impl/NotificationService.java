package com.ecommerce.notification.service.impl;

import java.text.MessageFormat;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.notification.domain.Notification;
import com.ecommerce.notification.service.INotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements INotificationService {

	@Autowired
	private final JavaMailSender mailSender;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${exchange.name}")
    private String exchange;

    @Value("${routing.key}")
    private String routingKey;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	@Override
	public void sendEmail(String body, String email, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();		
		
		message.setFrom(emailFrom);
		message.setTo(email);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		log.info(MessageFormat.format("email enviado para {0} com sucesso.", email));
	}

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
	public void sendEmail(Notification notification) {
		
		//Jogar na fila de produtos e pegar o id		
		int orderId = 100;
		
		String response = webClient.get()
										.uri("/user/" + notification.getUser_id())
										.retrieve()
										.bodyToMono(String.class)
										.block();
		
		Map<String, Object> map = convertToObject(response);		
		String email = map.get("email").toString();		
		String userName = map.get("name").toString();		
		String body = MessageFormat.format("Parabéns {0} seu pedido {1} foi finalizado e está em processo de coleta da transportadora",userName, orderId);
		
		sendEmail(body, email, notification.getSubject());		
	}

	@Override
	public void sendMessageBroker(Notification notification) {
		String json = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			json = mapper.writeValueAsString(notification);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		rabbitTemplate.convertAndSend(exchange, routingKey, json);		
	}

	@Override
	public void sendEmail(String notification) {
		
		log.info("mensagem recebida");
		
	}

}
