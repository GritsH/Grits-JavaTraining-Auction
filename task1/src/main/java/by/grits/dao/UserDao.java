package by.grits.dao;

import by.grits.entities.people.User;

import java.util.List;

public interface UserDao {
  User get(int id) throws DaoException;

  User getByEmail(String email) throws DaoException;

  void add(User user) throws DaoException;

  void delete(int key) throws DaoException;

  List<User> getAll() throws DaoException;

  User signIn(String email, String password) throws DaoException;
}
