package com.example.service.customer;

import com.example.dao.CustomerDao;
import com.example.dao.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerDao dao;

    public void save(Customer customer) {
        dao.update(customer);
    }

    public List<Customer> findAllCustomers() {
        return dao.findAll().all();
    }
}
