package com.enricotrentin.addressbook.customer;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends Repository<CustomerEntity, String> {

    void save(CustomerEntity customer);

    CustomerEntity findById(@Param("reference") String reference);

}
