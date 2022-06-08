package by.grits.dao;

import by.grits.dao.exception.DaoException;
import by.grits.entities.items.Item;

import java.util.List;

public interface ItemDao {
  Item getEntityById(int id);

  void add(Item item) throws DaoException;

  void delete(int key) throws DaoException;

  List<Item> getAll() throws DaoException;

  List<Item> getUserItems(String ownersEmail) throws DaoException;
}
