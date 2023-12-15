package com.pixzoo.back.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixzoo.back.model.Customer;
import com.pixzoo.back.repository.CustomerRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UniqueCpfInterceptor implements HandlerInterceptor {

    private final CustomerRepository customerRepository;

    @Autowired
    public UniqueCpfInterceptor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("POST")) {
            ObjectMapper mapper = new ObjectMapper();
            Customer userRequest = mapper.readValue(request.getInputStream(), Customer.class);

            if (userRequest.getCPF() != null && !userRequest.getCPF().isEmpty()) {
                try {
                    Customer existingCustomer = customerRepository.findByCPF(userRequest.getCPF());
                    if (existingCustomer != null) {
                    	response.setStatus(HttpStatus.CONFLICT.value());
                        //writeJsonResponse(response, "Conflict: Multiple customers found with the same CPF");
                        return false;
                    }
                } catch (Exception e) {
                	response.setStatus(HttpStatus.CONFLICT.value());
                    //writeJsonResponse(response, "Conflict: Multiple customers found with the same CPF");
                    return false;
                }
            }
        }
        return true;
    }
    

}
