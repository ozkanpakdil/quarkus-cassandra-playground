package com.example.ressource;


import com.example.ressource.dto.CustomerDto;
import com.example.ressource.mapper.CustomerMapper;
import com.example.service.customer.CustomerService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @GET
    @Path("/{customerNumber}")
    public List<CustomerDto> getPagedCustomersByCustomerNumber(@PathParam("customerNumber") String customerNumber,
                                                               @QueryParam("pageNumber") int page,
                                                               @QueryParam("pageSize") int offset) {
        return customerService.findCustomersByCustomerNumber(customerNumber, page, offset).stream()
                .map(c -> customerMapper.toDto(c))
                .collect(Collectors.toList());
    }

    @POST
    public void addCustomer(CustomerDto customerDto) {
        customerDto.setState("IN_PROGRESS");
        customerDto.setCreationDate(LocalDate.now());
        customerService.save(customerMapper.toEntity(customerDto));
    }

}
