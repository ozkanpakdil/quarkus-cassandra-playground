package com.example.service.customer;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.dao.CustomerDao;
import com.example.dao.PageableCustomerDao;
import com.example.dao.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerDao dao;

    @Inject
    PageableCustomerDao extendedDao;

    @Inject
    CqlSession session;

    public void save(Customer customer) {
        dao.update(customer);
    }

    public List<Customer> findAllCustomers() {
        return dao.findAll().all();
    }

    public List<Customer> findCustomersByCustomerNumber(String customerNumber, int pageNumber, int pageSize ) {
        return extendedDao.findPagedCustomerByCustomerNumber(customerNumber, pageNumber, pageSize);

    }
}
