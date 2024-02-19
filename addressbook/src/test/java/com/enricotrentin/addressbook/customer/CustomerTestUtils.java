package com.enricotrentin.addressbook.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class CustomerTestUtils {

    private static final String REFERENCE = "sholmes";
    private static final String NAME = "Sherlock Holmes";
    private static final String ADDRESS_LINE_1 = "221b, Baker street";
    private static final String ADDRESS_LINE_2 = "Marylebone";
    private static final String TOWN = "London";
    private static final String COUNTY = "Greater London";
    private static final String COUNTRY = "United Kingdom";
    private static final String POSTCODE = "NW1 6XE";

    private static final ObjectMapper objectMapper = new JsonMapper();

    public static Customer getCustomer () {
        return Customer.builder()
                .reference(REFERENCE)
                .name(NAME)
                .addressLine1(ADDRESS_LINE_1)
                .addressLine2(ADDRESS_LINE_2)
                .town(TOWN)
                .county(COUNTY)
                .country(COUNTRY)
                .postcode(POSTCODE)
                .build();
    }

    public static CustomerEntity getCustomerEntity () {
        return CustomerEntity.builder()
                .reference(REFERENCE)
                .name(NAME)
                .addressLine1(ADDRESS_LINE_1)
                .addressLine2(ADDRESS_LINE_2)
                .town(TOWN)
                .county(COUNTY)
                .country(COUNTRY)
                .postcode(POSTCODE)
                .build();
    }

    public static String toJsonString(Customer customer) throws JsonProcessingException {
        return objectMapper.writeValueAsString(customer);
    }

    public static String getNotFoundReference () {
        return "jwatson";
    }
}
