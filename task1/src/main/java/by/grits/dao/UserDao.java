package by.grits.dao;

import by.grits.entities.people.User;

import java.util.Collection;

public interface UserDao {
  User get(int id);

  User getByEmail(String email);

  void add(User user) throws DaoException;

  void delete(int key) throws DaoException;

  Collection<User> getAll() throws DaoException;

  User signIn(String email, String password);
}
