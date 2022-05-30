package com.example.dao;

import com.datastax.oss.quarkus.test.CassandraTestResource;
import com.example.dao.entity.Customer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(CassandraTestResource.class)
@Slf4j
class PageableCustomerDaoImplTest {

    @Inject
    PageableCustomerDao pageableCustomerDao;

    @Test
    void findPagedCustomerByCustomerNumber() {
        List<Customer> customers = pageableCustomerDao.findPagedCustomerByCustomerNumber("numero-uno", 1, 1);
        assertEquals(customers.size(), 1);

    }

    @Test
    void findPagedCustomerByCustomerNumberPaging() {
        List<Customer> customers = pageableCustomerDao.findPagedCustomerByCustomerNumber("numero-uno", 1, 1);
        assertEquals(customers.size(), 1);
        log.info("c:{}", customers.get(0));
    }
}