package by.grits.services;

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
  public void addItem(String name, String description, String ownersPhone, ItemType itemType) {
    Item item = new Item(name, description, ownersPhone, itemType);
    itemDao.add(item);
  }

  @Override
  public void removeItem(int key) {
    itemDao.delete(key);
  }

  @Override
  public Map<Integer, Item> getAllItems(String ownersPhone) {
    return itemDao.getUserItems(ownersPhone);
  }

  @Override
  public int getId(Item i) {
    return itemDao.getId(i);
  }

  @Override
  public void removeAll(String ownersPhone) {
    List<Item> items = new ArrayList<>(itemDao.getAll().values());
    for (Item i : items) {
      if (Objects.equals(i.getOwnersPhone(), ownersPhone)) {
        itemDao.delete(itemDao.getId(i));
      }
    }
  }
}
