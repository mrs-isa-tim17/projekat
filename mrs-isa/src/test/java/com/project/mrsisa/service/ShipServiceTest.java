package com.project.mrsisa.service;

import com.project.mrsisa.domain.ReservationReport;
import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.domain.ShipOwner;
import com.project.mrsisa.repository.ShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.project.mrsisa.constants.ReservationReportConstants.RESERVATION_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipServiceTest {

    @Mock
    private ShipRepository shipRepositoryMock;

    @Mock
    private Ship shipMock;

    @InjectMocks
    private ShipService shipService;

    @Test
    public void findShipsByOwner(){
        ShipOwner shipOwner = new ShipOwner();
        shipOwner.setId(1L);
        List<Ship> allShips = new ArrayList<>();
        Ship ship1  = new Ship();
        ship1.setId(7L);
        ship1.setOwner(shipOwner);
        allShips.add(ship1);

        Ship ship2  = new Ship();
        ship2.setId(8L);
        ship2.setOwner(shipOwner);
        allShips.add(ship1);
        shipOwner.setShips(allShips);

        when(shipRepositoryMock.findByOwner(shipOwner)).thenReturn(allShips);
        List<Ship> ships = shipService.getShipsByOwner(shipOwner);

        assertEquals(allShips, ships);
    }

}
