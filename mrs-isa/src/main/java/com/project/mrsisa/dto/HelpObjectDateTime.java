package com.project.mrsisa.dto;

import java.time.LocalDateTime;

public class HelpObjectDateTime {
    LocalDateTime start;
    LocalDateTime end;

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
