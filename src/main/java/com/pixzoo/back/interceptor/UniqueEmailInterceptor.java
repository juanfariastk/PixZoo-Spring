package com.pixzoo.back.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixzoo.back.config.JsonMessage;
import com.pixzoo.back.model.Customer;
import com.pixzoo.back.repository.CustomerRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class UniqueEmailInterceptor implements HandlerInterceptor {

    private final CustomerRepository customerRepository;

    @Autowired
    public UniqueEmailInterceptor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("POST")) {
            ObjectMapper mapper = new ObjectMapper();
            Customer userRequest = mapper.readValue(request.getInputStream(), Customer.class);

            if (userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) {
                try {
                    Customer existingCustomer = customerRepository.findByEmail(userRequest.getEmail());
                    if (existingCustomer != null) {
                        response.setStatus(HttpStatus.CONFLICT.value());
                        JsonMessage.writeJsonResponse(response, "Conflict: Email already exists in the database");
                        return false;
                    }
                } catch (Exception e) {
                	//System.out.println(e);
                	response.setStatus(HttpStatus.CONFLICT.value());
                    JsonMessage.writeJsonResponse(response, "Conflict: Email already exists in the database");
                    return false;
                }
            }
        }
        return true;
    }
}
