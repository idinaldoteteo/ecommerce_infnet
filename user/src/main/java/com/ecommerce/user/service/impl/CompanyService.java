package com.ecommerce.user.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.user.domain.Company;
import com.ecommerce.user.repository.ICompanyRepository;
import com.ecommerce.user.service.ICompanyService;

@Service
public class CompanyService extends GenericService<Company, Long, ICompanyRepository> implements ICompanyService{

	public CompanyService(ICompanyRepository repository) {
		super(repository);
	}

}
