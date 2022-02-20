package by.grits.dao;

import by.grits.entities.item.Item;

import java.util.HashMap;
import java.util.Map;

public class InMemoryItemDao implements ItemDao {

  private Map<Integer, Item> items = new HashMap<>();
  private int key = 0;

  @Override
  public Item getEntityById(int id) {
    return items.get(id);
  }

  @Override
  public void add(Item item) {
    items.put(key, item);
    key++;
  }

  @Override
  public void delete(int key) {
    items.remove(key);
  }

  @Override
  public Map<Integer, Item> getAll() {
    return items;
  }

  @Override
  public int getId(Item item) {
    for (Map.Entry<Integer, Item> entry : items.entrySet()) {
      if (entry.getValue().equals(item)) {
        return entry.getKey();
      }
    }
    return -1;
  }

  @Override
  public Map<Integer, Item> getUserItems(String ownersPhone) {
    Map<Integer, Item> itemList = new HashMap<>();
    for (Item i : items.values()) {
      if (i.getOwnersPhone() == ownersPhone) {
        itemList.put(getId(i), i);
      }
    }
    return itemList;
  }
}
