package com.project.mrsisa.controller;

import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mrsisa.dto.AdminUserDTO;
import com.project.mrsisa.dto.AdventureDTO;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private ShipOwnerService shipOwnerService;
    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private ReservationService reservationService;


    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        System.out.println("IN CONTROLLER");

        List<User> users = userService.findAllActive();
        System.out.println("ACTIVEEEE : " + users.size());

        List<AdminUserDTO> userDTO = new ArrayList<AdminUserDTO>();
        for (User user : users) {
            Role role = roleService.findOneById(user.getRoleId());
            System.out.println(role.getName());
            if(role.getId() == 2){
                continue;
            }
            AdminUserDTO newUser = new AdminUserDTO(user.getId(), user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail(), role.getName(), user.getLoyaltyPoints(),
                    user.getUserType().toString(), 0, user.isDeleted());
            if (role.getName() == "ROLE_CLIENT") {
                Client client = (Client) user;
                newUser.setPenalties(client.getPenaltyNumber());
            }
            userDTO.add(newUser);

        }
        return new ResponseEntity<List<AdminUserDTO>>(userDTO, HttpStatus.OK);

    }

    @GetMapping(value = "/all/deleted")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminUserDTO>> getDeletedUsers() {
        System.out.println("IN CONTROLLER");

        List<User> users = userService.findAllDeleted();
        System.out.println("ACTIVEEEE : " + users.size());

        List<AdminUserDTO> userDTO = new ArrayList<AdminUserDTO>();
        for (User user : users) {
            Role role = roleService.findOneById(user.getRoleId());
            System.out.println(role.getName());
            AdminUserDTO newUser = new AdminUserDTO(user.getId(), user.getName(), user.getSurname(), user.getPhoneNumber(), user.getEmail(), role.getName(), user.getLoyaltyPoints(),
                    user.getUserType().toString(), 0, user.isDeleted());
            if (role.getName() == "ROLE_CLIENT") {
                Client client = (Client) user;
                newUser.setPenalties(client.getPenaltyNumber());
            }
            userDTO.add(newUser);

        }
        return new ResponseEntity<List<AdminUserDTO>>(userDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            if (user.getRoleId() == 3) { // co
                CottageOwner owner = cottageOwnerService.findOne(user.getId());
                List<Cottage> cottages = cottageService.getCottagesByOwner(owner);
                for (Cottage c : cottages) {
                    if (reservationService.getFutureHistoryReservation(c.getId()).size() > 0) {
                        return ResponseEntity.ok(false);
                    }
                }
            } else if (user.getRoleId() == 4) { // ship
                ShipOwner shipOwner = shipOwnerService.findOne(user.getId());
                List<Ship> ships = shipService.getShipsByOwner(shipOwner);
                for (Ship c : ships) {
                    if (reservationService.getFutureHistoryReservation(c.getId()).size() > 0) {
                        return ResponseEntity.ok(false);
                    }
                }
            } else if (user.getRoleId() == 5) { // fi
                FishingInstructor instructor = fishingInstructorService.findOne(user.getId());
                List<Adventure> adventures = adventureService.getAdventuresByOwner(instructor);
                for (Adventure c : adventures) {
                    if (reservationService.getFutureHistoryReservation(c.getId()).size() > 0) {
                        return ResponseEntity.ok(false);
                    }
                }
            }else if (user.getRoleId() == 1){
                if (reservationService.getFutureActiveReservationsForClient(user.getId()).size() > 0){
                    return ResponseEntity.ok(false);
                }
            }
            user.setDeleted(true);
            user.setEnabled(false);
            userService.save(user);                // logičko brisanje
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
    }


}
