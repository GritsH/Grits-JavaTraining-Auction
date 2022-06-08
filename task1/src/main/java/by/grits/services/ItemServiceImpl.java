package by.grits.services;

import by.grits.entities.items.Item;
import by.grits.dao.exception.DaoException;
import by.grits.dao.ItemDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Class service which works with dao of items to get the information from it. */
public class ItemServiceImpl implements ItemService {

  private final ItemDao itemDao;

  public ItemServiceImpl(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void addItem(Item item) throws DaoException {
    itemDao.add(item);
  }

  @Override
  public void removeItem(int key) throws DaoException {
    itemDao.delete(key);
  }

  @Override
  public List<Item> getAllItems(String ownersEmail) throws DaoException {
    List<Item> userItems = itemDao.getUserItems(ownersEmail);
    Collections.sort(userItems);
    return userItems;
  }

  @Override
  public void removeAll(String ownersEmail) throws DaoException {
    List<Item> items = new ArrayList<>(itemDao.getAll());
    for (Item item : items) {
      if (Objects.equals(item.getOwnersEmail(), ownersEmail)) {
        itemDao.delete(item.getId());
      }
    }
  }
}
