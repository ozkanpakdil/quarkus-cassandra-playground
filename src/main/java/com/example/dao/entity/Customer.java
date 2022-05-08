package com.example.dao.entity;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity(defaultKeyspace = "remi")
@PropertyStrategy(mutable = false)
public class Customer {

    @PartitionKey
    private UUID id;
    private LocalDate creationDate;
    private String customerNumber;
    private String description;
    private String state;
}