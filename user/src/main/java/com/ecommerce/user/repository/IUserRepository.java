package com.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.user.domain.User;

public interface IUserRepository extends JpaRepository<User, Long>{

}
