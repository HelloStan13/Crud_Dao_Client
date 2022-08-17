package com.project1.DAO;

import com.project1.model.Client;

import java.util.List;
import java.util.Optional;

public class ClientDAOImple implements ClientDAO<Client> {
    @Override
    public Optional<Client> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void update(Client client, String[] params) {

    }

    @Override
    public void delete(Client client) {

    }
}
