package com.example.retailstoreschedule.Entity;

import java.time.LocalDate;

public class AssignShiftRequest {
    private Long shiftId;
    private LocalDate date;

    public AssignShiftRequest(Long shiftId, LocalDate date) {
        this.shiftId = shiftId;
        this.date = date;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public LocalDate getDate() {
        return date;
    }
}