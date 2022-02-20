package by.grits.services;

import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;

import java.util.Map;

public interface ItemService {
  void addItem(String name, String description, String ownersPhone, ItemType itemType);

  void removeItem(int key);

  Map<Integer, Item> getAllItems(String phone);

  int getId(Item i);

  void removeAll(String participantCard);
}
