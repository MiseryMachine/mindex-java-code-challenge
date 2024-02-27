package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompensationServiceImplUnitTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private CompensationRepository compensationRepository;

    private CompensationService compensationService;

    @Before
    public void init() {
        compensationService = new CompensationServiceImpl(compensationRepository, employeeService);
    }

    @Test
    public void create_success() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        double salary = 120_000;
        LocalDate effectiveDate = LocalDate.now();
        Employee employee = createTestEmployee(employeeId);
        Compensation compensation = new Compensation();
        compensation.setEmployee(employee);
        compensation.setSalary(salary);
        compensation.setEffectiveDate(effectiveDate);

        when(employeeService.read(employeeId)).thenReturn(employee);
        when(compensationRepository.insert(any(Compensation.class))).thenReturn(compensation);
        Compensation result = compensationService.create(employeeId, salary, effectiveDate);

        assertNotNull(result);
        assertEquals(employeeId, compensation.getEmployee().getEmployeeId());
        assertEquals(salary, compensation.getSalary(), 0.1);
        assertEquals(effectiveDate, compensation.getEffectiveDate());
    }

    @Test
    public void read_success() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        double salary = 120_000;
        LocalDate effectiveDate = LocalDate.now();
        Employee employee = createTestEmployee(employeeId);
        Compensation compensation = new Compensation();
        compensation.setEmployee(employee);
        compensation.setSalary(salary);
        compensation.setEffectiveDate(effectiveDate);

        when(compensationRepository.findByEmployeeId(employeeId)).thenReturn(Optional.of(compensation));
        Compensation result = compensationService.read(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, compensation.getEmployee().getEmployeeId());
        assertEquals(salary, compensation.getSalary(), 0.1);
        assertEquals(effectiveDate, compensation.getEffectiveDate());
    }

    private Employee createTestEmployee(String employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Manager");
        employee.setDepartment("Engineering");

        return employee;
    }
}
