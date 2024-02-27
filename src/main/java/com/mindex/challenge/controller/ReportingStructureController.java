package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Rest controller specifying endpoints for accessing employees' reporting structure.
 */
@RestController("reportingStructureController")
@RequestMapping("/reportingStructure")
public class ReportingStructureController {
    private final ReportingStructureService reportingStructureService;

    /**
     * Creates a new controller instance and autowires Spring Beans.
     *
     * @param reportingStructureService Service used to access reporting structure data.
     */
    public ReportingStructureController(ReportingStructureService reportingStructureService) {
        this.reportingStructureService = reportingStructureService;
    }

    /**
     * Endpoint to create a ReportingStructure instance for a employee id.  The number of reports are recursively
     * calculated.  The ReportingStructure instance is returned as JSON in the response body.
     *
     * @param emaployeeId The employee's id
     * @return A response entity with the created ReportingStructure as the payload.
     * @throws ResponseStatusException For any errors during the execution of this endpoint.
     */
    @GetMapping(value = "/create/{employeeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportingStructure> createReportingStructure(@PathVariable("employeeId") String emaployeeId) throws ResponseStatusException {
        ReportingStructure reportingStructure = reportingStructureService.getReportingStructure(emaployeeId);

        if (reportingStructure == null) {
            throw new ResponseStatusException(NOT_FOUND, "Employee not found");
        }

        return ResponseEntity.ok(reportingStructure);
    }
}
