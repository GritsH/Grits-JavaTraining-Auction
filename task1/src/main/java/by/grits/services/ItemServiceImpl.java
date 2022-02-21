package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.ItemDao;
import by.grits.entities.enums.ItemType;
import by.grits.entities.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemServiceImpl implements ItemService {

  private final ItemDao itemDao;

  public ItemServiceImpl(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override
  public void addItem(String name, String description, String ownersPhone, ItemType itemType) throws DaoException {
    try{
      Item item = new Item(name, description, ownersPhone, itemType);
      itemDao.add(item);
    }catch(Exception e){
      throw new DaoException(e);
    }
  }

  @Override
  public void removeItem(int key) throws DaoException {
    try{
      itemDao.delete(key);
    }catch(Exception e){
      throw new DaoException(e);
    }
  }

  @Override
  public Map<Integer, Item> getAllItems(String ownersPhone) throws DaoException {
    return itemDao.getUserItems(ownersPhone);
  }

  @Override
  public void removeAll(String ownersPhone) throws DaoException {
    try{
      List<Item> items = new ArrayList<>(itemDao.getAll().values());
      for (Item i : items) {
        if (Objects.equals(i.getOwnersEmail(), ownersPhone)) {
          itemDao.delete(i.getId());
        }
      }
    }catch(Exception e){
      throw new DaoException(e);
    }

  }
}
