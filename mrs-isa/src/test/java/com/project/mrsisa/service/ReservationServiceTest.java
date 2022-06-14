package com.project.mrsisa.service;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.project.mrsisa.domain.AdditionalServices;
import org.springframework.transaction.annotation.Transactional;

import static com.project.mrsisa.constants.AdditionalServicesConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;


    @Test
    public void testAddAdditionalServicesToPrice() {
        List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
        AdditionalServices a1 = new AdditionalServices(NEW_NAME_1, NEW_PRICE_1);
        additionalServices.add(a1);
        AdditionalServices a2 = new AdditionalServices(NEW_NAME_2, NEW_PRICE_2);
        additionalServices.add(a2);
        double price = 0;
        price = reservationService.addAdditionalServicesToPrice(price, additionalServices);

        assertEquals(120, price, 2);

    }

    @Test
    @Transactional
    public void testGetCottageHistoryReservation(){
        reservationService.setReservationRepository(reservationRepository);
        List<Reservation> res = reservationService.getCottageHistoryReservation(1L);
        assertEquals(1, res.size());
    }


}
