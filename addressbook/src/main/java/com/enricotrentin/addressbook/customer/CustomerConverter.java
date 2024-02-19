package com.enricotrentin.addressbook.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public Customer convert (CustomerEntity customer) {
        return Customer.builder()
                .reference(customer.getReference())
                .name(customer.getName())
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .town(customer.getTown())
                .county(customer.getCounty())
                .country(customer.getCountry())
                .postcode(customer.getPostcode())
                .build();
    }
    public CustomerEntity convert (Customer customer) {
        return CustomerEntity.builder()
                .reference(customer.getReference())
                .name(customer.getName())
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .town(customer.getTown())
                .county(customer.getCounty())
                .country(customer.getCountry())
                .postcode(customer.getPostcode())
                .build();
    }

}
