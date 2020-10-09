package com.psu.service;

import com.psu.entity.Client;
import com.psu.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void saveClient(Client client){
        clientRepository.save(client);
    }
}
