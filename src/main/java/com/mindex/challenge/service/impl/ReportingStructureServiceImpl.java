package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring Boot service for working with the ReportingStructure.
 */
@Service("reportingStructureService")
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private final Logger logger = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    private final EmployeeService employeeService;

    /**
     * Creates a new service instance and autowires Spring Beans.
     *
     * @param employeeService Service used to look up employee data.
     */
    public ReportingStructureServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ReportingStructure getReportingStructure(String employeeId) {
        Employee employee;

        try {
            employee = employeeService.read(employeeId);
        } catch (Exception e) {
            logger.error("Error looking up employee", e);
            return null;
        }

        if (employee == null) {
            return null;
        }

        int numberOfReports = countNumberOfReports(employee);

        return new ReportingStructure(employee, numberOfReports);
    }

    // Recursive method for rolling up direct report count.
    private int countNumberOfReports(Employee employee) {
        List<Employee> directReports = employee.getDirectReports();

        if (directReports != null) {
            List<String> employeeIds = directReports.stream()
                    .map(Employee::getEmployeeId)
                    .collect(Collectors.toList());

            return employeeIds.stream()
                    .map(employeeService::read)
                    .map(this::countNumberOfReports)
                    .reduce(directReports.size(), Integer::sum);
        }

        return 0;
    }
}
