package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationDto;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Rest controller specifying endpoints for accessing employees' compensation data.
 */
@RestController("compensationController")
@RequestMapping("/compensation")
public class CompensationController {
    private final Logger logger = LoggerFactory.getLogger(CompensationController.class);

    private final CompensationService compensationService;

    /**
     * Creates a new controller instance and autowires Spring Beans.
     *
     * @param compensationService Service used to access compensation data.
     */
    public CompensationController(CompensationService compensationService) {
        this.compensationService = compensationService;
    }

    /**
     * Endpoint to create compensation for an employee.  The request body requires the employeeId, salary value and
     * effective date.  Once created, the compensation data is returned as JSON in the response body.
     *
     * @param compensationDto The request body.
     * @return The created compensation record.
     * @throws ResponseStatusException For any errors while creating the compensation data.
     */
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Compensation> create(@RequestBody CompensationDto compensationDto) throws ResponseStatusException {
        if (compensationDtoInValid(compensationDto)) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid request body");
        }

        try {
            Compensation compensation = compensationService.create(compensationDto.getEmployeeId(),
                    compensationDto.getSalary(), compensationDto.getEffectiveDate());

            return ResponseEntity.ok(compensation);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve compensation for an employee.  The employee id is a path parameter.  Once retrieved, the
     * compensation data is returned as JSON in the response body.
     *
     * @param employeeId The employee's id
     * @return The compensation record.
     * @throws ResponseStatusException For any errors while retrieving the compensation data.
     */
    @GetMapping(value = "/read/{employeeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Compensation> read(@PathVariable("employeeId") String employeeId) throws ResponseStatusException {
        try {
            Compensation compensation = compensationService.read(employeeId);

            return ResponseEntity.ok(compensation);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // Validates the request body data
    private boolean compensationDtoInValid(CompensationDto dto) {
        return dto.getEmployeeId() == null || dto.getEmployeeId().trim().isEmpty() || dto.getSalary() < 0 || dto.getEffectiveDate() == null;
    }
}
