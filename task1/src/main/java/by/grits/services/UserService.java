package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.entities.people.User;

public interface UserService {
  User logIn(String email, String password) throws DaoException;

  void addNewUser(User user) throws DaoException;

  boolean userExists(String email) throws DaoException;

  void logOut() throws DaoException;
}
