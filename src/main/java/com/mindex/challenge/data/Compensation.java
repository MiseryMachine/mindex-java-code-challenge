package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

/**
 * A record/document containing compensation information based on employee.
 */
public class Compensation {
    @DBRef
    private Employee employee;
    private double salary;
    private LocalDate effectiveDate;

    public Compensation() {
    }

    /**
     * Get the employee instance.
     *
     * @return The employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the employee instance.
     *
     * @param employee The employee.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
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
