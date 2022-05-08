package com.example.ressource;


import com.example.ressource.dto.CustomerDto;
import com.example.ressource.mapper.CustomerMapper;
import com.example.service.customer.CustomerService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/api/v1/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerRessource {

    @Inject
    CustomerService customerService;
    @Inject
    CustomerMapper customerMapper;

    @GET
    public List<CustomerDto> getAllCustomers() {
        return customerService.findAllCustomers().stream()
                .map(c -> customerMapper.toDto(c))
                .collect(Collectors.toList());
    }

    @POST
    public void addCustomer(CustomerDto customerDto) {
        customerDto.setId(UUID.randomUUID());
        customerDto.setState("IN_PROGRESS");
        customerDto.setCreationDate(LocalDate.now());
        customerService.save(customerMapper.toEntity(customerDto));
    }

}
