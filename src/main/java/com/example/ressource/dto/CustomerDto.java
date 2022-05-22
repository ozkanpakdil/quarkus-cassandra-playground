package com.example.ressource.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CustomerDto {
    private LocalDate creationDate;
    private String customerNumber;
    private String description;
    private String state;
}
