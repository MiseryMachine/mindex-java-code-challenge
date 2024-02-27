package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String domain;

    @Before
    public void init() {
        domain = String.format("http://localhost:%d/reportingStructure", port);
    }

    @Test
    public void testCreate_success() {
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ResponseEntity<ReportingStructure> response = restTemplate.getForEntity(String.format("%s/create/%s", domain, employeeId),
                ReportingStructure.class, new HashMap<>());

        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        ReportingStructure reportingStructure = response.getBody();
        assertNotNull(reportingStructure);
        assertEquals(employeeId, reportingStructure.getEmployee().getEmployeeId());
        assertEquals(4, reportingStructure.getNumberOfReports());
    }

    @Test
    public void testCreate_notFound() {
        String employeeId = "invalid";
        ResponseEntity<ReportingStructure> response = restTemplate.getForEntity(String.format("%s/create/%s", domain, employeeId),
                ReportingStructure.class, new HashMap<>());

        assertNotNull(response);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }
}
