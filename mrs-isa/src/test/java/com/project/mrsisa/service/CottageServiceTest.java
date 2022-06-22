package com.project.mrsisa.service;

import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.repository.CottageOwnerRepository;
import com.project.mrsisa.repository.CottageRepository;
import com.project.mrsisa.repository.ReservationRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.project.mrsisa.constants.CottageConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CottageServiceTest {

    @Mock
    private CottageRepository cottageRepositoryMock;
     @Autowired
     private ReservationService reservationService;
     @Autowired
     private ReservationRepository reservationRepository;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    @Mock
    private Cottage cottageMock;

    @InjectMocks
    private CottageService cottageService;

    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private CottageService cService;

    @Test
    public void findOneCottageTest(){
        when(cottageRepositoryMock.findById(COTTAGE_ID)).thenReturn(Optional.of(cottageMock));
        Cottage cottage = cottageService.findOne(COTTAGE_ID);
        assertEquals(cottageMock, cottage);
    verify(cottageRepositoryMock, times(1)).findById(COTTAGE_ID);
    verifyNoMoreInteractions(cottageRepositoryMock);
    }

    @Before
    public void fillServices(){
        cottageOwnerService.setCottageOwnerRepository(cottageOwnerRepository);
        cService.setCottageRepository(cottageRepository);
        reservationService.setReservationRepository(reservationRepository);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addCottageTest(){
        List<Cottage> cottageList = new ArrayList<>();
        Cottage c = new Cottage();
        c.setId(5L);
        c.setName("Medica");
        c.setDescription("Prelepo mesto sa pogledom na reku.");
        CottageOwner co = cottageOwnerService.findOne(3L);

        //cottageList.add(c);
       // co.setCottages(cottageList);
       // c.setOwner(co);
        Cottage cottage = new Cottage();
        //cottage.setId(COTTAGE_ID);
       // cottage.setId(5L);
        cottage.setName(COTTAGE_NAME);
        cottage.setDescription(COTTAGE_DESCRIPTION);
        cottage.setImages(new ArrayList<>());
        cottage.setRoomQuantity(ROOM_QUANTITY);
        cottage.setDeleted(false);
        cottage.setBedQuantity(BED_QUANTITY);
        cottage.setOwner(co);
        cottage.setAdditionalServices(new ArrayList<>());
        cottage.setBehaviorRules(new ArrayList<>());
        cottage.setComplaints(new ArrayList<>());
        cottage.setReservations(new ArrayList<>());
        cottage.setClient(new ArrayList<>());
        cottage.setExperienceReviews(new ArrayList<>());
        cottage.setCancelCondition(new ArrayList<>());
        cottage.setPeriodAvailabilities(new ArrayList<>());
        cottage.setPeriodUnavailabilities(new ArrayList<>());
        cottage.setPricelists(new ArrayList<>());
        cottage.setSaleAppointments(new ArrayList<>());
        cottage.setAddress(new Address());


        when(cottageRepositoryMock.save(cottage)).thenReturn(cottage);

        // 2. Akcija
        int dbSizeBeforeAdd = cService.getCottagesByOwner(co).size();

       Cottage dbCottage = cService.save(cottage);
       System.out.println("cottage" + dbCottage.getId());

        assertThat(dbCottage).isNotNull();

        List<Cottage> allCottagesForOwner = cService.getCottagesByOwner(co);
        assertThat(allCottagesForOwner).hasSize(dbSizeBeforeAdd + 1);

        dbCottage = allCottagesForOwner.get(allCottagesForOwner.size() - 1);

        assertThat(dbCottage.getDescription()).isEqualTo(COTTAGE_DESCRIPTION);
        assertThat(dbCottage.getName()).isEqualTo(COTTAGE_NAME);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeCottageTest(){
        CottageOwner co = cottageOwnerService.findOne(3L);
        List<Cottage> cottages  = cService.getCottagesByOwner(co);
        Cottage before = cottages.get(cottages.size() - 1);
        System.out.println(before.isDeleted());
        assertFalse(before.isDeleted());
        cService.deleteCottage(before.getId());
        List<Cottage> cottagesAfter  = cService.getCottagesByOwner(co);
        Cottage after = cottages.get(cottagesAfter.size() - 1);
        assertTrue(after.isDeleted());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateCottageOwnerProfileTest(){
        CottageOwner owner = cottageOwnerService.findOne(3L);
        owner.setName(NEW_NAME);
        owner.setPhoneNumber(NEW_PHONE);
        owner = cottageOwnerService.save(owner);
        assertThat(owner).isNotNull();

        owner = cottageOwnerService.findOne(3L);

        assertThat(owner.getName()).isEqualTo(NEW_NAME);
        assertThat(owner.getPhoneNumber()).isEqualTo(NEW_PHONE);

    }



}
