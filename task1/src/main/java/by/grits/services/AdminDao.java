package by.grits.services;

import by.grits.dao.Dao;
import by.grits.entities.people.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminDao implements Dao<Admin> {
    private List<Admin> admins = new ArrayList<>();

    @Override
    public Admin getById(int id) {
        return admins.get(id);
    }

    @Override
    public void add(Admin admin) {

    }

    @Override
    public void delete(Admin admin) {

    }

    @Override
    public List<Admin> getAll() {
        return null;
    }


}
