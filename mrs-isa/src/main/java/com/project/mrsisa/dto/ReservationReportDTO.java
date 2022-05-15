package com.project.mrsisa.dto;

public class ReservationReportDTO {
    private int reservationId;
    private String report;
    private boolean suggestedPenalty;
    private boolean unarrivedClient;

    public ReservationReportDTO() {
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public boolean isSuggestedPenalty() {
        return suggestedPenalty;
    }

    public void setSuggestedPenalty(boolean suggestedPenalty) {
        this.suggestedPenalty = suggestedPenalty;
    }

    public boolean isUnarrivedClient() {
        return unarrivedClient;
    }

    public void setUnarrivedClient(boolean unarrivedClient) {
        this.unarrivedClient = unarrivedClient;
    }
}
