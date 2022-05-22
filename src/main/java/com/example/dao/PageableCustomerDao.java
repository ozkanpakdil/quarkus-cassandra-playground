package com.example.dao;

import com.example.dao.entity.Customer;

import java.util.List;

public interface PageableCustomerDao {

    List<Customer> findPagedCustomerByCustomerNumber(String customerNumber, int pageNumber, int pageSize);
}
