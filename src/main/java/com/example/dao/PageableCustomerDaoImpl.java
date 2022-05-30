package com.example.dao;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.core.paging.OffsetPager;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.example.dao.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.bindMarker;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

@ApplicationScoped
public class PageableCustomerDaoImpl implements PageableCustomerDao {

    @Inject
    CqlSession cqlSession;

    @Override
    public List<Customer> findPagedCustomerByCustomerNumber(String customerNumber, int pageNumber, int pageSize) {
        List<Customer> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM test.customer WHERE customer_number = :customerNumber ORDER BY  creation_date");
        BoundStatement completeStatement = query.bind().setString("customerNumber", customerNumber);
        OffsetPager pager = new OffsetPager(pageSize);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        OffsetPager.Page<Row> page = pager.getPage(resultSet, pageNumber);
        List<Row> pageElements = page.getElements();
        pageElements.forEach(c -> result.add(Customer.builder()
                .customerNumber(c.getString("customer_number"))
                .creationDate(c.getLocalDate("creation_date"))
                .description(c.getString("description"))
                .state(c.getString("state"))
                .build())
        );
        return result;
    }

    @Override
    public List<Customer> findPagedCustomerByCustomerNumberPaging(String customerNumber, int pageNumber, int pageSize) {
        List<Customer> result = new ArrayList<>();
        Select select = QueryBuilder.selectFrom("test", "customer")
                .columns("customer_number", "creation_date", "description", "state")
                .whereColumn("customer_number").isEqualTo(literal(customerNumber))
                .orderBy("createtion_date", ClusteringOrder.ASC)
                .allowFiltering();

        SimpleStatement completeStatement = select.build();
        OffsetPager pager = new OffsetPager(pageSize);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        OffsetPager.Page<Row> page = pager.getPage(resultSet, pageNumber);
        List<Row> pageElements = page.getElements();
        pageElements.forEach(c -> result.add(Customer.builder()
                .customerNumber(c.getString("customer_number"))
                .creationDate(c.getLocalDate("creation_date"))
                .description(c.getString("description"))
                .state(c.getString("state"))
                .build())
        );
        return result;
    }
}
