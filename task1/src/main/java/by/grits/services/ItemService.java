package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.entities.items.Item;

import java.util.Collection;

public interface ItemService {
  void addItem(Item item) throws DaoException;

  void removeItem(int key) throws DaoException;

  Collection<Item> getAllItems(String ownersEmail) throws DaoException;

  void removeAll(String ownersEmail) throws DaoException;
}
