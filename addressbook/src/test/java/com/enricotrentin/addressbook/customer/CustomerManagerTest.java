package com.enricotrentin.addressbook.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerManagerTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerConverter customerConverter;
    @InjectMocks
    CustomerManager customerManager;

    @Test
    void getCustomerTest () {
        Customer expected = CustomerTestUtils.getCustomer();
        CustomerEntity customerEntity = CustomerTestUtils.getCustomerEntity();

        Mockito.when(customerRepository.findById(expected.getReference()))
                .thenReturn(customerEntity);
        Mockito.when(customerConverter.convert(customerEntity))
                .thenReturn(expected);

        Customer customer = customerManager.getCustomer(expected.getReference());

        Assertions.assertEquals(expected, customer);
    }

    @Test
    void getCustomerNotFoundTest () {
        String notFoundReference = CustomerTestUtils.getNotFoundReference();

        Mockito.when(customerRepository.findById(notFoundReference))
                .thenReturn(null);

        Customer customer = customerManager.getCustomer(notFoundReference);

        Assertions.assertNull(customer);
    }

    @Test
    void createCustomerTest () {
        Customer input = CustomerTestUtils.getCustomer();
        CustomerEntity customerEntity = CustomerTestUtils.getCustomerEntity();

        Mockito.when(customerConverter.convert(input))
                .thenReturn(customerEntity);

        customerManager.createCustomer(input);

        Mockito.verify(customerRepository).save(customerEntity);
    }
}
