package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.domain.LoyaltyPoints;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.domain.Role;
import com.project.mrsisa.domain.UserType;
import com.project.mrsisa.domain.UserTypeLoyaltyPoints;
import com.project.mrsisa.dto.LoyaltyPointsDTO;
import com.project.mrsisa.dto.LoyaltyScaleDTO;
import com.project.mrsisa.service.LoyaltyPointsService;
import com.project.mrsisa.service.LoyaltyScaleService;
import com.project.mrsisa.service.RoleService;

@RestController
@RequestMapping(value="/loyalty", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoyaltyScaleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private LoyaltyScaleService loyaltyScaleService;
	@Autowired
	private LoyaltyPointsService loyaltyPointsService;
	
	
	@GetMapping(value="scale/all/{role}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<LoyaltyScaleDTO>> getLoyaltyScaleForRole(@PathVariable String role) {
		System.out.println("IN CONTROLLER");
		Role r = (roleService.findByName(role)).get(0);
		
		List<LoyaltyScale> scales = loyaltyScaleService.findScaleByRole(r.getId());
		List<LoyaltyScaleDTO> scalesDTO = new ArrayList<LoyaltyScaleDTO>();
		
		for(LoyaltyScale l : scales) {
			scalesDTO.add(new LoyaltyScaleDTO(l.getId(), l.getDiscount(), l.getStartDate(), l.getEndDate(), l.getTrashhold(), l.getUserType().toString(), role));
		}
		return new ResponseEntity<List<LoyaltyScaleDTO>>(scalesDTO, HttpStatus.OK);

	}
	
	@PostMapping(value="scale/define/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LoyaltyScaleDTO> defineNewLoyaltyScale(@RequestBody LoyaltyScaleDTO loyaltyScaleDTO, @PathVariable Long id) {
		
		LoyaltyScale old = loyaltyScaleService.findOneById(id);
		old.setEndDate(loyaltyScaleDTO.getStartDate().minusDays(1));
		loyaltyScaleService.save(old);
		
		Role r = (roleService.findByName(loyaltyScaleDTO.getRole())).get(0);
		LoyaltyScale newScale = new LoyaltyScale();
		newScale.setEndDate(null);
		newScale.setDiscount(loyaltyScaleDTO.getDiscount());
		newScale.setStartDate(loyaltyScaleDTO.getStartDate());
		newScale.setTrashhold(loyaltyScaleDTO.getTrashold());
		newScale.setUserType(UserType.valueOf(loyaltyScaleDTO.getUserType()));
		newScale.setRoles(r);
		
		loyaltyScaleService.save(newScale);
		
		return new ResponseEntity<>(loyaltyScaleDTO, HttpStatus.OK);
	
	}
	
	
	@GetMapping(value="points/all/{type}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<LoyaltyPointsDTO>> getLoyaltyPointsForType(@PathVariable String type) {

		List<LoyaltyPoints> points = loyaltyPointsService.findPointsByUserTypePoints(UserTypeLoyaltyPoints.valueOf(type));
		
		List<LoyaltyPointsDTO> pointsDTO = new ArrayList<LoyaltyPointsDTO>();

		for(LoyaltyPoints l : points) {
			pointsDTO.add(new LoyaltyPointsDTO(l.getId(), l.getStartDate(), l.getEndDate(),l.getPoints(), l.getUserTypeLoyaltyPoints().toString()));
		}	
		return new ResponseEntity<List<LoyaltyPointsDTO>>(pointsDTO, HttpStatus.OK);
		
	}
	
	
	@PostMapping(value="points/define")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<LoyaltyPointsDTO>> defineNewPoints(@RequestBody LoyaltyPointsDTO loyaltyPointsDTO) {
		
		LoyaltyPoints old = loyaltyPointsService.findActivePointsByType(UserTypeLoyaltyPoints.valueOf(loyaltyPointsDTO.getType()));
		
		old.setEndDate(loyaltyPointsDTO.getStartDate().minusDays(1));
		loyaltyPointsService.save(old);
		
		LoyaltyPoints newPoints = new LoyaltyPoints();
		newPoints.setEndDate(null);
		newPoints.setPoints(loyaltyPointsDTO.getPoints());
		newPoints.setStartDate(loyaltyPointsDTO.getStartDate());
		newPoints.setUserTypeLoyaltyPoints(old.getUserTypeLoyaltyPoints());
		
		loyaltyPointsService.save(newPoints);
		
		
		
		
		List<LoyaltyPoints> points = loyaltyPointsService.findPointsByUserTypePoints(UserTypeLoyaltyPoints.valueOf(loyaltyPointsDTO.getType()));
		
		List<LoyaltyPointsDTO> pointsDTO = new ArrayList<LoyaltyPointsDTO>();

		for(LoyaltyPoints l : points) {
			pointsDTO.add(new LoyaltyPointsDTO(l.getId(), l.getStartDate(), l.getEndDate(),l.getPoints(), l.getUserTypeLoyaltyPoints().toString()));
		}	
		
		return new ResponseEntity<>(pointsDTO, HttpStatus.OK);
		
	}

}
