package by.grits.dao;

import by.grits.entities.item.Item;

import java.util.Map;

public interface ItemDao {
  Item getEntityById(int id);

  void add(Item item);

  void delete(int key);

  Map<Integer, Item> getAll();

  int getId(Item item);

  Map<Integer, Item> getUserItems(String ownersPhone);
}
