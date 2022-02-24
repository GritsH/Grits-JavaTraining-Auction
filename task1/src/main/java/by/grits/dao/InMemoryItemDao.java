package by.grits.dao;

import by.grits.entities.items.Item;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/** This class is responsible for different operations with imitated table of database. */
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
      item.setAddedAt(LocalDate.now());
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
  public List<Item> getAll() throws DaoException {
    try {
      return new ArrayList<>(items.values());
    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Item> getUserItems(String ownersEmail) throws DaoException {
    try {
      List<Item> itemList = new ArrayList<>();
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
