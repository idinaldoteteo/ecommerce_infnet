package com.ecommerce.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.domain.User;
import com.ecommerce.user.service.IUserService;

@RestController	
@RequestMapping("/api/user")
public class UserController extends GenericController<User>{
	
    public UserController(IUserService service){ super(service); }
    
}
