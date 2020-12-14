package com.concordusa.unittestdemo.service;

import com.concordusa.unittestdemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentService extends CrudRepository <Student, Long>
{

}
