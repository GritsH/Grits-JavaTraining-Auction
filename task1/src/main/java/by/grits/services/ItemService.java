package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;

import java.util.Map;

public interface ItemService {
  void addItem(String name, String description, String ownersPhone, ItemType itemType) throws DaoException;

  void removeItem(int key) throws DaoException;

  Map<Integer, Item> getAllItems(String phone) throws DaoException;

  void removeAll(String participantCard) throws DaoException;
}
