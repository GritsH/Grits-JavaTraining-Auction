package by.grits.services;

import by.grits.entities.items.Item;
import by.grits.dao.exception.DaoException;

import java.util.List;

public interface ItemService {
  void addItem(Item item) throws DaoException;

  void removeItem(int key) throws DaoException;

  List<Item> getAllItems(String ownersEmail) throws DaoException;

  void removeAll(String ownersEmail) throws DaoException;
}
