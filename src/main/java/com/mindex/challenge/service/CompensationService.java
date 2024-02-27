package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

import java.time.LocalDate;

/**
 * Specifies services for working with the Compensation bean.
 */
public interface CompensationService {
    /**
     * Creates and persists a new compensation record.
     *
     * @param employeeId    The employee id.
     * @param salary        The employee salary.
     * @param effectiveDate The effective date.
     * @return The Compensation instance created from persisting the data.
     */
    Compensation create(String employeeId, double salary, LocalDate effectiveDate);

    /**
     * Reads compensation data from persistence, based on a given employee id.
     *
     * @param employeeId The employee id.
     * @return The Compensation instance read from persistence.
     */
    Compensation read(String employeeId);
}
