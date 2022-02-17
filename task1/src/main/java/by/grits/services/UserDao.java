package by.grits.services;

import by.grits.dao.Dao;
import by.grits.entities.people.CommonUser;
import by.grits.entities.people.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<CommonUser> {
    private List<CommonUser> users = new ArrayList<>();

    @Override
    public CommonUser getById(int id) {
        return users.get(id);
    }

    @Override
    public void add(CommonUser commonUser) {
        users.add(commonUser);
    }

    @Override
    public void delete(CommonUser commonUser) {
        users.remove(commonUser);
    }

    @Override
    public List<CommonUser> getAll() {
        return users;
    }

    public boolean userExists(String email, String phoneNumber){
        for (CommonUser u:
             users) {
            if(u.getPhoneNumber().equals(phoneNumber) || u.getEmailAddress().equals(email)){
                return true;
            }
        }
        return false;
    }

    public CommonUser getUserByLoginPassword(String login, String password){
        for (CommonUser u: users) {
            if(u.getLogin().equals(login) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }
}
