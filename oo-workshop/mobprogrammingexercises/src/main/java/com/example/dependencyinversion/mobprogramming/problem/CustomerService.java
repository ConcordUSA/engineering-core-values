package com.example.dependencyinversion.mobprogramming.problem;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final SimpleCustomerDao simpleCustomerDao;

    public CustomerService(SimpleCustomerDao simpleCustomerDao) {
        this.simpleCustomerDao = simpleCustomerDao;
    }

    public Optional<Customer> findById(int id) {
        return simpleCustomerDao.findById(id);
    }

    public List<Customer> findAll() {
        return simpleCustomerDao.findAll();
    }
}
