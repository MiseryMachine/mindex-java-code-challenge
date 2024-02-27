package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * A data transfer object that can be used as a detached bean from the {@link Compensation} bean.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompensationDto {
    private String employeeId;
    private double salary;
    private LocalDate effectiveDate;

    public CompensationDto() {
    }

    /**
     * Get the employee's id.
     *
     * @return The employee id.
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the employee's id.
     *
     * @param employeeId The employee id.
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the employee's salary.
     *
     * @return The salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Set the employee's salary.
     *
     * @param salary The salary.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Get the effective date.
     *
     * @return The effective date.
     */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Set the effective date.
     *
     * @param effectiveDate The effective date.
     */
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
