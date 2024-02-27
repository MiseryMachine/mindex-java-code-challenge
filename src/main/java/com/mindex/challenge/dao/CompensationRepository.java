package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CompensationRepository extends MongoRepository<Compensation, Employee> {
    @Query("{'employee.employeeId': ?0}")
    Optional<Compensation> findByEmployeeId(String employeeId);
}
