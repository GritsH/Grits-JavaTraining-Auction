package by.grits.dao;

import by.grits.entities.items.Item;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryItemDao implements ItemDao {

  private final AtomicInteger idCounter = new AtomicInteger();
  private Map<Integer, Item> items = new HashMap<>();

  @Override
  public Item getEntityById(int id) {
    return items.get(id);
  }

  @Override
  public void add(Item item) throws DaoException {
    try {
      int id = idCounter.incrementAndGet();
      item.setAddedAt(new Date(System.currentTimeMillis()));
      item.setId(id);
      items.put(id, item);
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void delete(int key) throws DaoException {
    try {
      items.remove(key);
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Collection<Item> getAll() throws DaoException {
    try {
      return items.values();
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Collection<Item> getUserItems(String ownersEmail) throws DaoException {
    try {
      Collection<Item> itemList = new HashSet<>();
      for (Item item : items.values()) {
        if (Objects.equals(item.getOwnersEmail(), ownersEmail)) {
          itemList.add(item);
        }
      }
      return itemList;
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}
