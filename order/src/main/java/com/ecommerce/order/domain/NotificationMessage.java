package com.ecommerce.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {

	private Long order_id;
	
	private Integer order_status;
	
	private Long user_id;
	
	private String subject;
}
