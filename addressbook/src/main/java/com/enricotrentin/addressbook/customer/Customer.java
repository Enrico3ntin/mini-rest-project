package com.enricotrentin.addressbook.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String reference;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;
}
