package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Spring Boot service for working with the Compensation bean.
 */
@Service("compensationService")
public class CompensationServiceImpl implements CompensationService  {
    private final CompensationRepository compensationRepository;
    private final EmployeeService employeeService;

    /**
     * Creates a new service instance and autowires Spring Beans.
     *
     * @param compensationRepository The persistence repository for accessing compensation data.
     * @param employeeService        The service for accessing employee data.
     */
    public CompensationServiceImpl(CompensationRepository compensationRepository, EmployeeService employeeService) {
        this.compensationRepository = compensationRepository;
        this.employeeService = employeeService;
    }

    public Compensation create(String employeeId, double salary, LocalDate effectiveDate) {
        Employee employee = employeeService.read(employeeId);
        Compensation compensation = new Compensation();
        compensation.setEmployee(employee);
        compensation.setSalary(salary);
        compensation.setEffectiveDate(effectiveDate);

        return compensationRepository.insert(compensation);
    }

    public Compensation read(String employeeId) {
        Optional<Compensation> opt = compensationRepository.findByEmployeeId(employeeId);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new RuntimeException("Compensation not found");
    }
}
