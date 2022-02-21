package by.grits.dao;

import by.grits.entities.item.Item;

import java.util.Map;

public interface ItemDao {
  Item getEntityById(int id);

  void add(Item item) throws DaoException;

  void delete(int key) throws DaoException;

  Map<Integer, Item> getAll() throws DaoException;

  Map<Integer, Item> getUserItems(String ownersPhone) throws DaoException;
}
