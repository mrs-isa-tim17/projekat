package com.project.mrsisa.dto.client;

import java.time.LocalDate;
import java.util.List;

public class QuickReservationForClientDTO {
    private LocalDate startSaleDate;
    private double duration;
    private int peopleQuantity;
    private double discount;
    private double price;
    private List<String> additionalServices;
    /*

	@GetMapping(value = "site/quick/{id}")
	public ResponseEntity<QuickReservationForClientDTO> getQuickReservations(){

	}
     */
}
