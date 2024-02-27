package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String domain;

    @Before
    public void init() {
        domain = String.format("http://localhost:%d/compensation", port);
    }

    @Test
    public void create() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        double salary = 120_000;
        LocalDate effectiveDate = LocalDate.now();
        CompensationDto dto = new CompensationDto();
        dto.setEmployeeId(employeeId);
        dto.setSalary(salary);
        dto.setEffectiveDate(effectiveDate);

        ResponseEntity<Compensation> response = restTemplate.postForEntity(String.format("%s/create", domain), dto,
                Compensation.class, new HashMap<>());

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());

        Compensation compensation = response.getBody();
        assertNotNull(compensation);
        assertEquals(employeeId, compensation.getEmployee().getEmployeeId());
        assertEquals(salary, compensation.getSalary(), 0.1);
        assertEquals(effectiveDate, compensation.getEffectiveDate());
    }

    @Test
    public void read() {
        // First insert the test data to be read
        String employeeId = "b7839309-3348-463b-a7e3-5de1c168beb3";
        double salary = 90_000;
        LocalDate effectiveDate = LocalDate.now();
        CompensationDto dto = new CompensationDto();
        dto.setEmployeeId(employeeId);
        dto.setSalary(salary);
        dto.setEffectiveDate(effectiveDate);

        ResponseEntity<Compensation> response = restTemplate.postForEntity(String.format("%s/create", domain), dto,
                Compensation.class, new HashMap<>());

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());

        // Now test reading it
        response = restTemplate.getForEntity(String.format("%s/read/%s", domain, employeeId), Compensation.class, new HashMap<>());

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());

        Compensation compensation = response.getBody();
        assertNotNull(compensation);
        assertEquals(employeeId, compensation.getEmployee().getEmployeeId());
        assertEquals(salary, compensation.getSalary(), 0.1);
        assertEquals(effectiveDate, compensation.getEffectiveDate());
    }
}
