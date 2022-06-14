package com.project.mrsisa.dto;

import java.time.LocalDateTime;

public class PeriodDTO {
    private LocalDateTime fromDate;
    private LocalDateTime untilDate;

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate.plusHours(2);
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate.plusHours(2);
    }
}
