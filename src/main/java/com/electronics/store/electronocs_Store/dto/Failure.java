package com.electronics.store.electronocs_Store.dto;

import java.time.LocalDate;

public class Failure {
    private String failureCause;
    private LocalDate failureDate;
    private LocalDate reportedDate;
    private String reportedBy;
    private String failureRemarks;

    // Getters and Setters
    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause;
    }

    public LocalDate getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(LocalDate failureDate) {
        this.failureDate = failureDate;
    }

    public LocalDate getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(LocalDate reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getFailureRemarks() {
        return failureRemarks;
    }

    public void setFailureRemarks(String failureRemarks) {
        this.failureRemarks = failureRemarks;
    }
}
