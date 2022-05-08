package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CustomerResourceTest {

    @Test
    public void testCustomerEndpoint() {
        given()
                .when().get("/api/v1/customer")
                .then()
                .statusCode(200);
    }

}