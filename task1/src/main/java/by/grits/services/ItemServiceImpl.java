package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.ItemDao;
import by.grits.entities.items.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
  public Collection<Item> getAllItems(String ownersEmail) throws DaoException {
    return itemDao.getUserItems(ownersEmail);
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
