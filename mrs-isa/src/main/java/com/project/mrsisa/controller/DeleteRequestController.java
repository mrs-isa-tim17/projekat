package com.project.mrsisa.controller;

import com.project.mrsisa.domain.DeleteRequest;
import com.project.mrsisa.domain.ProcessingStatus;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.DeleteRequestDTO;
import com.project.mrsisa.dto.UserTokenState;
import com.project.mrsisa.service.DeleteRequestService;
import com.project.mrsisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class DeleteRequestController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeleteRequestService deleteRequestService;

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<Boolean> deleteAccountRequest(@PathVariable("id") long id, @RequestBody DeleteRequestDTO deleteRequestDTO) {
        User u = userService.findById(id);
        DeleteRequest dr = new DeleteRequest();
        dr.setUserRef(u);
        dr.setStatus(ProcessingStatus.UNPROCESSED);
        String reason = deleteRequestDTO.getReason();
        if (reason == "")
            return ResponseEntity.ok(false);
        System.out.println(reason);
        dr.setText(reason);//deleting = from the end of the string
        deleteRequestService.save(dr);
        return null;
    }

}