package com.project.mrsisa.service;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.dto.ReserveEntityDTO;
import com.project.mrsisa.exception.AlreadyCanceled;
import com.project.mrsisa.exception.NotAvailable;
import com.project.mrsisa.exception.NotDefinedValue;
import com.project.mrsisa.repository.*;
import com.sun.istack.Nullable;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
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

    @Autowired
    private CottageService cottageService;

    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private PeriodAvailabilitySerivce periodAvailabilitySerivce;
    @Autowired
    private PeriodAvailabilityRepository periodAvailabilityRepository;
    @Autowired
    private PeriodUnavailabilityService periodUnavailabilityService;
    @Autowired
    private PeriodUnavailabilityRepository periodUnavailabilityRepository;
    @Autowired
    private PricelistService pricelistService;
    @Autowired
    private PricelistRepository pricelistRepository;



    @InjectMocks
    private ReservationService reservationService;

    @Before
    public void fillMockService(){
        reservationService.setReservationRepository(reservationRepository);

        cottageService.setCottageRepository(cottageRepository);
        reservationService.setCottageService(cottageService);

        periodAvailabilitySerivce.setPeriodAvailabilityRepository(periodAvailabilityRepository);
        reservationService.setPeriodAvailabilitySerivce(periodAvailabilitySerivce);

        periodUnavailabilityService.setPeriodUnavailabilityRepository(periodUnavailabilityRepository);
        reservationService.setPeriodUnavailabilityService(periodUnavailabilityService);

        pricelistService.setPricelistRepository(pricelistRepository);
        reservationService.setPricelistService(pricelistService);
    }

    @Test()
    @javax.transaction.Transactional
    @Rollback(true)
    public void testReserveEntitySuccess(){

        ReserveEntityDTO reservationDTO = new ReserveEntityDTO();
        reservationDTO.setOfferId(1);
        reservationDTO.setOfferType("cottage");
        reservationDTO.setChosenAdditionalServices(new ArrayList<String>());
        reservationDTO.setFromDate(LocalDateTime.of(2022, 01, 10, 14, 15));
        reservationDTO.setUntilDate(LocalDateTime.of(2022, 01, 15, 8, 15));
        reservationDTO.setShipOwnerPresent(false);
        Reservation r = new Reservation();
        reservationService.reserveEntity(reservationDTO, r);
    }

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
        List<Reservation> res = reservationService.getAllReservationsForOffer(1L);
        assertEquals(1, res.size());
    }

    @Test(expected = NotDefinedValue.class)
    @javax.transaction.Transactional
    @Rollback(true)
    public void testReserveEntityOfferTypeNotValid(){
        reservationService.setReservationRepository(reservationRepository);
       ReserveEntityDTO reservationDTO = new ReserveEntityDTO();
       reservationDTO.setOfferId(1);
       reservationDTO.setOfferType("tiiip");
       reservationDTO.setChosenAdditionalServices(new ArrayList<String>());
       reservationDTO.setFromDate(LocalDateTime.of(2022, 06, 10, 14, 15));
       reservationDTO.setFromDate(LocalDateTime.of(2022, 06, 15, 8, 15));
       reservationDTO.setShipOwnerPresent(false);
       Reservation r = new Reservation();
       reservationService.reserveEntity(reservationDTO, r);
    }

    @Test(expected = NullPointerException.class)
    @javax.transaction.Transactional
    @Rollback(true)
    public void testReserveEntityOfferIdNotExisting(){
        ReserveEntityDTO reservationDTO = new ReserveEntityDTO();
        reservationDTO.setOfferId(100000);
        reservationDTO.setOfferType("cottage");
        reservationDTO.setChosenAdditionalServices(new ArrayList<String>());
        reservationDTO.setFromDate(LocalDateTime.of(2022, 06, 10, 14, 15));
        reservationDTO.setFromDate(LocalDateTime.of(2022, 06, 15, 8, 15));
        reservationDTO.setShipOwnerPresent(false);
        Reservation r = new Reservation();
        reservationService.reserveEntity(reservationDTO, r);
    }

    @Test(expected = NotAvailable.class)
    @javax.transaction.Transactional
    @Rollback(true)
    public void testReserveEntityOfferNotAvailableAnymore(){
        ReserveEntityDTO reservationDTO = new ReserveEntityDTO();
        reservationDTO.setOfferId(1);
        reservationDTO.setOfferType("cottage");
        reservationDTO.setChosenAdditionalServices(new ArrayList<String>());
        reservationDTO.setFromDate(LocalDateTime.of(2022, 9, 22, 14, 15));
        reservationDTO.setUntilDate(LocalDateTime.of(2022, 9, 29, 8, 15));
        reservationDTO.setShipOwnerPresent(false);
        Reservation r1 = new Reservation();
        reservationService.reserveEntity(reservationDTO, r1);
    }

}
