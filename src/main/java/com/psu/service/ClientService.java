package com.psu.service;

import com.psu.entity.Client;
import com.psu.entity.Employee;
import com.psu.entity.Excursion;
import com.psu.entity.User;
import com.psu.repository.ClientRepository;
import com.psu.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ExcursionRepository excursionRepository;

    public List<Client> allClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public List<Excursion> allExcursion() {
        return (List<Excursion>) excursionRepository.findAll();
    }

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
