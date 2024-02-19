package com.enricotrentin.addressbook.customer;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class CustomerController {

    @Autowired
    CustomerManager customerManager;

    @GetMapping("customer/{reference}")
    public @ResponseBody Customer getCustomer (
            @PathVariable(name="reference") String reference, HttpServletResponse response) {
        log.info("Received request: get customer; reference='{}'", reference);
        Customer customer = customerManager.getCustomer(reference);
        if (customer == null) {
            log.info("Returning response: customer not found; reference='{}'", reference);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        log.info("Returning response: customer found; reference='{}'", reference);
        response.setStatus(HttpServletResponse.SC_OK);
        return customer;
    }

    @PostMapping("customer")
    public void createCustomer (@RequestBody() Customer customer, HttpServletResponse response) {
        log.info("Received request: create customer; reference='{}'", customer.getReference());
        customerManager.createCustomer(customer);
        log.info("Returning response: customer created; reference='{}'", customer.getReference());
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}
