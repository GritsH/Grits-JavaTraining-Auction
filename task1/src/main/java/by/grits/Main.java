package by.grits;

import by.grits.dao.DaoException;
import by.grits.io.Menu;

public class Main {

  public static void main(String[] args) throws DaoException {
    Menu menu = new Menu();
    menu.startMenu();
  }
}
