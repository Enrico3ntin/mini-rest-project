package com.enricotrentin.addressbook.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerManager {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerConverter customerConverter;

    public Customer getCustomer (String reference) {
        CustomerEntity customer = customerRepository.findById(reference);
        if (customer == null) {
            return null;
        }
        return customerConverter.convert(customer);
    }

    public void createCustomer (Customer customer) {
        CustomerEntity toBeSaved = customerConverter.convert(customer);
        customerRepository.save(toBeSaved);
    }

}
