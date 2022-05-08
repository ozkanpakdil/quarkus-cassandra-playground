package com.example.ressource.mapper;

import com.example.dao.entity.Customer;
import com.example.ressource.dto.CustomerDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .creationDate(customer.getCreationDate())
                .customerNumber(customer.getCustomerNumber())
                .description(customer.getDescription())
                .state(customer.getState())
                .build();
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .creationDate(customerDto.getCreationDate())
                .customerNumber(customerDto.getCustomerNumber())
                .description(customerDto.getDescription())
                .state(customerDto.getState())
                .build();
    }
}
