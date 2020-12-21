package com.example.dependencyinversion.mobprogramming.problem;

import com.example.dependencyinversion.mobprogramming.solution.Customer;
import com.example.dependencyinversion.mobprogramming.solution.SimpleCustomerDao;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCustomerDaoTest {
    private SimpleCustomerDao simpleCustomerDao;

    @Before
    public void setUpCustomerDaoInstance() {
        Map<Integer, Customer> customers = new HashMap<>();
        customers.put(1, new Customer("John"));
        customers.put(2, new Customer("Susan"));
        simpleCustomerDao = new SimpleCustomerDao(customers);
    }

    @Test
    public void givenCustomerDaoInstance_whenCalledFindById_thenCorrect() {
        assertThat(simpleCustomerDao.findById(1)).isInstanceOf(Optional.class);
    }

    @Test
    public void givenCustomerDaoInstance_whenCalledFindAll_thenCorrect() {
        assertThat(simpleCustomerDao.findAll()).isInstanceOf(List.class);
    }

    @Test
    public void givenCustomerDaoInstance_whenCalledFindByIdWithNullCustomer_thenCorrect() {
        //arrange
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
        customers.put(1, null);
        SimpleCustomerDao simpleCustomerDao = new SimpleCustomerDao(customers);

        //act
        Customer customer = simpleCustomerDao.findById(1).orElseGet(() -> new Customer("Non-existing customer"));

        //assert
        assertThat(customer.getName()).isEqualTo("Non-existing customer");
    }
}
