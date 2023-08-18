package com.innoventes.test.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innoventes.test.app.entity.Company;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {
	Optional<Company> getCompanyByCompanyCode(String companyCode);
}