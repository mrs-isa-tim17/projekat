package com.project.mrsisa.dto.client;

public class SuccessOfCancelReservationDTO {

    private boolean successful;
    private double taken;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public double getTaken() {
        return taken;
    }

    public void setTaken(double taken) {
        this.taken = taken;
    }
}
