package com.project.mrsisa.dto;

import com.project.mrsisa.domain.ReservationReport;

public class ReservationReportDTO {
    private Long reservationId;
    private Long clientId;
    private String reportText;
    private boolean suggestedPenallty;
    private boolean unarrivedClient;

    public ReservationReportDTO(){

    }
    public ReservationReportDTO(ReservationReport report) {
        clientId = report.getClientId();
        reservationId = report.getReservationId();
        suggestedPenallty = report.isPenallty();
        unarrivedClient = report.isUnarrivedClient();
        reportText = report.getReportText();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReport() {
        return reportText;
    }

    public void setReport(String report) {
        this.reportText = report;
    }

    public boolean isSuggestedPenallty() {
        return suggestedPenallty;
    }

    public void setSuggestedPenallty(boolean suggestedPenallty) {
        this.suggestedPenallty = suggestedPenallty;
    }

    public boolean isUnarrivedClient() {
        return unarrivedClient;
    }

    public void setUnarrivedClient(boolean unarrivedClient) {
        this.unarrivedClient = unarrivedClient;
    }
}
