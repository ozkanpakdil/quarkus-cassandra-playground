package com.example.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import com.example.dao.entity.Customer;

@Dao
public interface CustomerDao {
    @Select
    PagingIterable<Customer> findAll();

    @Update
    void update(Customer customer);

}
