package com.example;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.quarkus.test.CassandraTestResource;
import com.example.dao.CustomerDao;
import com.example.dao.entity.Customer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@QuarkusTest
@QuarkusTestResource(CassandraTestResource.class)
public class CustomerDaoTest {

    @Inject
    CustomerDao dao;

    //This test is not working yet
    @Test
    public void testInsertAndSelectOperations() {
        // Given
        Customer givenCustomer = Customer.builder()
                .id(UUID.fromString("123-456-789-ABC"))
                .creationDate(LocalDate.now())
                .customerNumber("123")
                .description("LeistungsbezeichnungTest")
                .state("IN_BEARBEITUNG")
                .build();
        //WHEN
        dao.update(givenCustomer);
        PagingIterable<Customer> pagingIterable = dao.findAll();
        //THEN
        List<Customer> allCustomers = pagingIterable.all();
        Assertions.assertThat(allCustomers).hasSize(1);
    }

}
