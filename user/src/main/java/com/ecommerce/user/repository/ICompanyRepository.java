package com.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.user.domain.Company;

public interface ICompanyRepository extends JpaRepository<Company, Long>{

}
