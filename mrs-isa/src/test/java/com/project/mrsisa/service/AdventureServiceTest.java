package com.project.mrsisa.service;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.repository.AdventureRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdventureServiceTest {
	
	@Autowired
	private AdventureService adventureService;
	@Autowired
	private AdventureRepository adventureRepository;
	
	@Autowired 
	private ReservationService reservationService;
	

    @Before
    public void fillMockService(){
    	adventureService.setAdventureRepository(adventureRepository);
    	adventureService.setReservationService(reservationService);
    	
    }
    
    @Test()
    @javax.transaction.Transactional
    @Rollback(true)
    public void testFindActiveAdventure(){

    	List<Adventure>activeAdventures = adventureService.findActiveAdventures();
    	assertEquals(2, activeAdventures.size());
    }

    @Test()
    @javax.transaction.Transactional
    @Rollback(true)
    public void testDeletedActiveAdventure(){

    	List<Adventure>deletedAdventures = adventureService.findDeletedAdventures();
    	assertEquals(1, deletedAdventures.size());
    }
    
    @Test()
    @javax.transaction.Transactional
    @Rollback(true)
    public void testDeleteAdventure(){
    	boolean answer = adventureService.deleteAdventure(3L);
    	assertFalse(answer);
    		
    }
    
    @Test()
    @javax.transaction.Transactional
    @Rollback(true)
    public void testDeleteAdventure2(){
    	boolean answer = adventureService.deleteAdventure(4L);
    	assertTrue(answer);
    		
    }

}
