package com.example.mobprogramming.solution;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDaoTest {
    private CustomerDao customerDao;

    @Before
    public void setUpCustomerDaoInstance() {
        Map<Integer, Customer> customers = new HashMap<>();
        customers.put(1, new Customer("John"));
        customers.put(2, new Customer("Susan"));
        customerDao = new SimpleCustomerDao(customers);
    }

    @Test
    public void givenCustomerDaoInstance_whenCalledFindById_thenCorrect() {
        assertThat(customerDao.findById(1)).isInstanceOf(Optional.class);
    }

    @Test
    public void givenCustomerDaoInstance_whenCalledFindAll_thenCorrect() {
        assertThat(customerDao.findAll()).isInstanceOf(List.class);
    }

    @Test
    public void givenCustomerDaoInstance_whenCalledFindByIdWithNullCustomer_thenCorrect() {
        //arrange
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
        customers.put(1, null);
        CustomerDao customerDaoObject = new SimpleCustomerDao(customers);

        //act
        Customer customer = customerDaoObject.findById(1).orElseGet(() -> new Customer("Non-existing customer"));

        //assert
        assertThat(customer.getName()).isEqualTo("Non-existing customer");
    }
}
