package com.project.mrsisa.service;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Offer;
import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.project.mrsisa.constants.CottageConstants.*;
import static com.project.mrsisa.constants.PeriodAvailabilityConstants.*;
import static com.project.mrsisa.constants.ReservationConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferServiceTest {
    @InjectMocks
    private OfferService offerService;

    @Test
    public void testIsDefinedAvailabilePeriod(){
        LocalDateTime searchedPeriodFrom = LocalDateTime.of(2022,5,20,10,10);
        LocalDateTime searchedPeriodUntil = LocalDateTime.of(2022,5,28,10,10);

        Offer o1 = new Cottage();
        o1.addPeriodAvailability(new PeriodAvailability(PERIOD_AVAILABILITY_FROM1, PERIOD_AVAILABILITY_UNTIL1));
        boolean res1 = offerService.isDefinedAvailabilePeriod(o1, searchedPeriodFrom, searchedPeriodUntil);
        assertTrue(res1);

        Offer o2 = new Cottage();
        o2.addPeriodAvailability(new PeriodAvailability(PERIOD_AVAILABILITY_FROM2, PERIOD_AVAILABILITY_UNTIL2));
        boolean res2 = offerService.isDefinedAvailabilePeriod(o2, searchedPeriodFrom, searchedPeriodUntil);
        assertFalse(res2);

        Offer o3 = new Cottage();
        o3.addPeriodAvailability(new PeriodAvailability(PERIOD_AVAILABILITY_FROM3, PERIOD_AVAILABILITY_UNTIL3));
        boolean res3 = offerService.isDefinedAvailabilePeriod(o3, searchedPeriodFrom, searchedPeriodUntil);
        assertFalse(res3);

        Offer o4 = new Cottage();
        o4.addPeriodAvailability(new PeriodAvailability(PERIOD_AVAILABILITY_FROM4, PERIOD_AVAILABILITY_UNTIL4));
        boolean res4 = offerService.isDefinedAvailabilePeriod(o4, searchedPeriodFrom, searchedPeriodUntil);
        assertFalse(res4);

        Offer o5 = new Cottage();
        o5.addPeriodAvailability(new PeriodAvailability(PERIOD_AVAILABILITY_FROM5, PERIOD_AVAILABILITY_UNTIL5));
        boolean res5 = offerService.isDefinedAvailabilePeriod(o5, searchedPeriodUntil,searchedPeriodFrom);
        assertFalse(res5);
    }

    @Test
    public void testIsDefinedReservation(){
        LocalDateTime searchedPeriodFrom = LocalDateTime.of(2022,5,20,10,10);
        LocalDateTime searchedPeriodUntil = LocalDateTime.of(2022,5,28,10,10);

        Offer o1 = new Cottage();
        o1.addReservation(new Reservation(RESERVATION_FROM1, RESERVATION_UNTIL1));
        boolean res1 = offerService.isDefinedReservation(o1, searchedPeriodFrom, searchedPeriodUntil);
        assertTrue(res1);

        Offer o2 = new Cottage();
        o2.addReservation(new Reservation(RESERVATION_FROM2, RESERVATION_UNTIL2));
        boolean res2 = offerService.isDefinedReservation(o2, searchedPeriodFrom, searchedPeriodUntil);
        assertTrue(res2);

        Offer o3 = new Cottage();
        o3.addReservation(new Reservation(RESERVATION_FROM3, RESERVATION_UNTIL3));
        boolean res3 = offerService.isDefinedReservation(o3, searchedPeriodFrom, searchedPeriodUntil);
        assertTrue(res3);

        Offer o4 = new Cottage();
        o4.addReservation(new Reservation(RESERVATION_FROM4, RESERVATION_UNTIL4));
        boolean res4 = offerService.isDefinedReservation(o4, searchedPeriodFrom, searchedPeriodUntil);
        assertFalse(res4);

        Offer o5 = new Cottage();
        o5.addReservation(new Reservation(RESERVATION_FROM5, RESERVATION_UNTIL5));
        boolean res5 = offerService.isDefinedReservation(o5, searchedPeriodFrom, searchedPeriodUntil);
        assertTrue(res5);

        boolean res6 = offerService.isDefinedReservation(o1, searchedPeriodUntil, searchedPeriodFrom);
        assertTrue(res6);
    }

    @Test
    public void testFilterByRoomQuntity(){
        List<Cottage> cottageList = fillCottageListWithRommQuantities();

        List<Cottage> resLT = offerService.filterByRoomQuntity(cottageList, 5, "<");
        assertEquals(3, resLT.size());

        List<Cottage> resGT = offerService.filterByRoomQuntity(cottageList, 5, ">");
        assertEquals(4, resGT.size());

        List<Cottage> resGE = offerService.filterByRoomQuntity(cottageList, 5, ">=");
        assertEquals(5, resGE.size());

        List<Cottage> resLE = offerService.filterByRoomQuntity(cottageList, 5, "<=");
        assertEquals(4, resLE.size());

        List<Cottage> resEQ = offerService.filterByRoomQuntity(cottageList, 7, "==");
        assertEquals(2, resEQ.size());

        List<Cottage> resZ = offerService.filterByRoomQuntity(cottageList, 0, "==");
        assertEquals(8, resZ.size());

        List<Cottage> resRelOp = offerService.filterByRoomQuntity(cottageList, 0, "");
        assertEquals(8, resRelOp.size());
    }

    private List<Cottage> fillCottageListWithRommQuantities() {

        List<Cottage> cottageList = new ArrayList<Cottage>();

        Cottage c1 = new Cottage();
        c1.setRoomQuantity(ROOM_QUANTITY1);
        cottageList.add(c1);

        Cottage c2 = new Cottage();
        c2.setRoomQuantity(ROOM_QUANTITY2);
        cottageList.add(c2);

        Cottage c3 = new Cottage();
        c3.setRoomQuantity(ROOM_QUANTITY3);
        cottageList.add(c3);

        Cottage c4 = new Cottage();
        c4.setRoomQuantity(ROOM_QUANTITY4);
        cottageList.add(c4);

        Cottage c5 = new Cottage();
        c5.setRoomQuantity(ROOM_QUANTITY5);
        cottageList.add(c5);

        Cottage c6 = new Cottage();
        c6.setRoomQuantity(ROOM_QUANTITY6);
        cottageList.add(c6);

        Cottage c7 = new Cottage();
        c7.setRoomQuantity(ROOM_QUANTITY7);
        cottageList.add(c7);

        Cottage c8 = new Cottage();
        c8.setRoomQuantity(ROOM_QUANTITY8);
        cottageList.add(c8);

        return cottageList;
    }
}
