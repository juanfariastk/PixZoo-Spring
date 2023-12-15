package com.pixzoo.back.repository;

import com.pixzoo.back.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
    Customer findByCPF(String cpf);
    
    Customer findByEmail(String email);
    
}