package com.example.timekeeping.service;

import com.example.timekeeping.model.Client;

import java.util.Collection;

public interface ClientService {
    Client create(Client client);
    Collection<Client> list();
    Client get(Long id);
    Client update(Client client);
    Boolean delete(Long id);
}
