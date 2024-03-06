package com.ecommerce.user.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.user.domain.User;
import com.ecommerce.user.repository.IUserRepository;
import com.ecommerce.user.service.IUserService;

@Service
public class UserService extends GenericService<User, Long, IUserRepository> implements IUserService{

	public UserService(IUserRepository repository) {
		super(repository);
	}

}

