package com.ecommerce.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.domain.Company;
import com.ecommerce.user.service.ICompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController extends GenericController<Company>{
	
    public CompanyController(ICompanyService service){ super(service); }
    
}
