package com.example.mobprogramming.problem;

import com.example.mobprogramming.solution.Customer;
import com.example.mobprogramming.solution.CustomerService;
import com.example.mobprogramming.solution.SimpleCustomerDao;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Map<Integer, com.example.mobprogramming.solution.Customer> customers = new HashMap<>();
        customers.put(1, new com.example.mobprogramming.solution.Customer("John"));
        customers.put(2, new Customer("Susan"));
        com.example.mobprogramming.solution.CustomerService customerService = new CustomerService(new SimpleCustomerDao(customers));
        customerService.findAll().forEach(System.out::println);
    }
}
