package com.psu.service;

import com.psu.entity.Client;
import com.psu.entity.User;
import com.psu.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public Client getClient(User user){
        return clientRepository.findByUser(user);
    }

    public void deleteClient(Client client){
        clientRepository.delete(client);
    }

}
