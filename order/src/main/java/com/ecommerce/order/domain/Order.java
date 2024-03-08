package com.ecommerce.order.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer status = 1;
	
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long user_id;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createAt;
	
	private LocalDateTime aprovetAt;
	
	private LocalDateTime confirmationTransportAt;
	
	private String transportName;
	
	private String clientName;
	
	private String productName;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER , cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected Set<OrderItem> orderItems = new HashSet<>();
	
	@PrePersist
	protected void onCreate() {
		createAt = LocalDateTime.now();		
	}
	
}
