package by.grits.dao;

import by.grits.entities.item.Item;

import java.util.Collection;

public interface ItemDao {
  Item getEntityById(int id);

  void add(Item item) throws DaoException;

  void delete(int key) throws DaoException;

  Collection<Item> getAll() throws DaoException;

  Collection<Item> getUserItems(String ownersEmail) throws DaoException;
}
