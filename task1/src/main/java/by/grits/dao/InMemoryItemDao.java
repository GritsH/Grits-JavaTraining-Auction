package by.grits.dao;

import by.grits.entities.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryItemDao implements ItemDao {

  private Map<Integer, Item> items = new HashMap<>();
  private final AtomicInteger idCounter = new AtomicInteger();

  @Override
  public Item getEntityById(int id) {
    return items.get(id);
  }

  @Override
  public void add(Item item) {
    int id = idCounter.incrementAndGet();
    item.setId(id);
    items.put(id, item);
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
  public Map<Integer, Item> getUserItems(String ownersPhone) {
    Map<Integer, Item> itemList = new HashMap<>();
    for (Item i : items.values()) {
      if (Objects.equals(i.getOwnersEmail(), ownersPhone)) {
        itemList.put(i.getId(), i);
      }
    }
    return itemList;
  }
}
