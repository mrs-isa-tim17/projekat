package com.project.mrsisa.dto.client;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Cottage;

public class CurrentClientDTO {
    private String clientEmail;
    private String clientName;
    private String clientSurname;
    private String offerName;

    public CurrentClientDTO(Client client, Cottage cottage) {
        this.clientEmail = client.getEmail();
        this.clientName = client.getName();
        this.clientSurname = client.getSurname();
        this.offerName = cottage.getName();
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
}
