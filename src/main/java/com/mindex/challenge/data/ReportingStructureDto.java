package com.mindex.challenge.data;

/**
 * A data transfer object for rolling up number of reports for each employee
 */
public class ReportingStructureDto {
    private final Employee employee;
    private final int numberOfReports;

    /**
     * Creates a new instance for the specified Employee and number of reports.
     *
     * @param employee        The employee.
     * @param numberOfReports The employee's total number of reports.
     */
    public ReportingStructureDto(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    /**
     * Gets the employee.
     *
     * @return The employee.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Gets the employee's total number of reports.
     *
     * @return The total number of reports.
     */
    public int getNumberOfReports() {
        return numberOfReports;
    }
}
