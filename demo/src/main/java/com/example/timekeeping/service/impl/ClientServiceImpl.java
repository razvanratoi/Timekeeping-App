package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.Client;
import com.example.timekeeping.repo.ClientRepo;
import com.example.timekeeping.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;

    @Override
    public Client create(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Collection<Client> list() {
        return clientRepo.findAll().stream().toList();
    }

    @Override
    public Client get(Long id) {
        return clientRepo.findById(id).get();
    }

    @Override
    public Client update(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Boolean delete(Long id) {
        clientRepo.deleteById(id);
        return true;
    }
}
