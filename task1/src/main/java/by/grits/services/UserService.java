package by.grits.services;

import by.grits.entities.people.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {
  User logIn(String login, String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException;

  void addNewUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;

  boolean userExists(String phoneNumber);
}
