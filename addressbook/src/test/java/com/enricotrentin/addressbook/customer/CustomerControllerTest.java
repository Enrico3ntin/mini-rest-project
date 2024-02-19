package com.enricotrentin.addressbook.customer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerManager customerManager;

    @Test
    void getCustomerTest () throws Exception {
        Customer testCustomer = CustomerTestUtils.getCustomer();
        String expected = CustomerTestUtils.toJsonString(testCustomer);

        Mockito.when(customerManager.getCustomer(testCustomer.getReference()))
            .thenReturn(testCustomer);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{reference}", testCustomer.getReference()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @Test
    void getNonExistingCustomerTest () throws Exception {
        String testReference = CustomerTestUtils.getNotFoundReference();
        Mockito.when(customerManager.getCustomer(testReference))
                .thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{reference}", testReference))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void createCustomerTest () throws Exception {
        Customer testCustomer = CustomerTestUtils.getCustomer();
        String body = CustomerTestUtils.toJsonString(testCustomer);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
