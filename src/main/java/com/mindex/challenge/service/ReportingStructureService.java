package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructureDto;

/**
 * Specifies services for working with the ReportingStructure.
 */
public interface ReportingStructureService {
    /**
     * Create a ReportingStructure instance for the employee with the given id.  If no employee with the id exists,
     * return null.
     *
     * @param employeeId The employee's id.
     * @return A ReportingStructure for the employee, or null if the employee is not found.
     */
    ReportingStructureDto getReportingStructure(String employeeId);
}
