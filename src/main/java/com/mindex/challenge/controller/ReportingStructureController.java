package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructureDto;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Rest controller specifying endpoints for accessing employees' reporting structure.
 */
@RestController("reportingStructureController")
@RequestMapping("/reportingStructure")
public class ReportingStructureController {
    private final Logger logger = LoggerFactory.getLogger(ReportingStructureController.class);

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
     * @param employeeId The employee's id
     * @return A response entity with the created ReportingStructure as the payload.
     * @throws ResponseStatusException For any errors during the execution of this endpoint.
     */
    @GetMapping(value = "/create/{employeeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportingStructureDto> createReportingStructure(@PathVariable("employeeId") String employeeId) throws ResponseStatusException {
        try {
            ReportingStructureDto reportingStructureDto = reportingStructureService.getReportingStructure(employeeId);

            return ResponseEntity.ok(reportingStructureDto);
        }
        catch (ResponseStatusException e) {
            logger.error(e.getMessage(), e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
