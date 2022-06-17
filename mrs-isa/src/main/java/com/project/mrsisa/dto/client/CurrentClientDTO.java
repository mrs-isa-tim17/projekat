package com.project.mrsisa.dto.client;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.domain.Ship;

import java.time.LocalDate;

public class CurrentClientDTO {
    private String clientEmail;
    private String clientName;
    private String clientSurname;
    private String offerName;
    private Long clientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long cottageId;


    public CurrentClientDTO(Client client, Cottage cottage, Reservation r) {
        this.clientEmail = client.getEmail();
        this.clientName = client.getName();
        this.clientSurname = client.getSurname();
        this.offerName = cottage.getName();
        this.clientId = client.getId();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.cottageId = cottage.getId();
    }

    public CurrentClientDTO(Client client, Ship ship, Reservation r) {
        this.clientEmail = client.getEmail();
        this.clientName = client.getName();
        this.clientSurname = client.getSurname();
        this.offerName = ship.getName();
        this.clientId = client.getId();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.cottageId = ship.getId();
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getCottageId() {
        return cottageId;
    }

    public void setCottageId(Long cottageId) {
        this.cottageId = cottageId;
    }
}




