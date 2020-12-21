package com.example.dependencyinversion.mobprogramming.solution;

import java.util.*;

public class SimpleCustomerDao implements CustomerDao {
    private Map<Integer, Customer> customers = new HashMap<>();

    public SimpleCustomerDao(Map<Integer, Customer> customers) {
        this.customers = customers;
    }

    @Override
    public Optional<Customer> findById(int id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}
