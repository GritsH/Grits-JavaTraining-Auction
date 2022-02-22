package by.grits;

import by.grits.dao.DaoException;
import by.grits.io.Menu;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {

  public static void main(String[] args)
      throws NoSuchAlgorithmException, InvalidKeySpecException, DaoException {
    Menu menu = new Menu();
    menu.startMenu();
  }
}
