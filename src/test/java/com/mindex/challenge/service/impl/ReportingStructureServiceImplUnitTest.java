package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingStructureServiceImplUnitTest {
    @Mock
    private EmployeeService employeeService;

    private ReportingStructureServiceImpl reportingStructureService;

    @Before
    public void init() {
        reportingStructureService = new ReportingStructureServiceImpl(employeeService);
    }

    @Test
    public void testCreate_success() {
        Employee top = buildEmployee();
        String employeeId = top.getEmployeeId();

        when(employeeService.read(employeeId)).thenReturn(top);
        ReportingStructure reportingStructure = reportingStructureService.getReportingStructure(employeeId);

        assertNotNull(reportingStructure);
        assertEquals(top.getDirectReports().size(), reportingStructure.getNumberOfReports());
    }

    private Employee buildEmployee() {
        Employee top = new Employee();
        top.setEmployeeId("abc");
        top.setFirstName("John");
        top.setLastName("Lennon");
        top.setPosition("Development Manager");
        top.setDepartment("Engineering");

        List<Employee> reports = new ArrayList<>();
        Employee report = new Employee();
        report.setEmployeeId("def");
        report.setFirstName("Paul");
        report.setLastName("McCartney");
        report.setPosition("Developer I");
        report.setDepartment("Engineering");
        reports.add(report);

        report = new Employee();
        report.setEmployeeId("ghi");
        report.setFirstName("Ringo");
        report.setLastName("Starr");
        report.setPosition("Developer V");
        report.setDepartment("Engineering");
        reports.add(report);

        top.setDirectReports(reports);

        return top;
    }
}
