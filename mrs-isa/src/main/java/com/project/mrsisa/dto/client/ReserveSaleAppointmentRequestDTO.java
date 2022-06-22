package com.project.mrsisa.dto.client;

public class ReserveSaleAppointmentRequestDTO {

    private long saleAppointmentId;
    private long clientId;

    public long getSaleAppointmentId() {
        return saleAppointmentId;
    }

    public void setSaleAppointmentId(long saleAppointmentId) {
        this.saleAppointmentId = saleAppointmentId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
