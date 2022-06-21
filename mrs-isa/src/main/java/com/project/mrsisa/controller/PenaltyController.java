package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PenaltyController {

    @Autowired
    private ClientService clientService;

    @Scheduled(cron="0 0 0 1 * ?") //brisanje penala, svakog prvog u mesecu
    public void clearPenallty(){
        List<Client> allClients = clientService.findAll();
        for(Client c : allClients){
            if(c.getPenaltyNumber() > 0){
                c.setPenaltyNumber(0);
                clientService.save(c);
            }
        }
    }
}
