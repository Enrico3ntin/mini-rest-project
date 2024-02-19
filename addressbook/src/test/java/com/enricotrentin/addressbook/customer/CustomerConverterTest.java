package com.enricotrentin.addressbook.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerConverterTest {

    private CustomerConverter customerConverter;

    @BeforeEach
    void setup () {
        customerConverter = new CustomerConverter();
    }

    @Test
    void convertCustomerTest () {
        Customer testInput = CustomerTestUtils.getCustomer();
        CustomerEntity expected = CustomerTestUtils.getCustomerEntity();

        CustomerEntity converted = customerConverter.convert(testInput);

        Assertions.assertEquals(expected, converted);
    }

    @Test
    void convertCustomerEntityTest () {
        CustomerEntity testInput = CustomerTestUtils.getCustomerEntity();
        Customer expected = CustomerTestUtils.getCustomer();

        Customer converted = customerConverter.convert(testInput);

        Assertions.assertEquals(expected, converted);
    }

}
