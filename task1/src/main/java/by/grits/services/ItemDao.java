package by.grits.services;

import by.grits.dao.Dao;
import by.grits.entities.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDao implements Dao<Item> {

  private List<Item> items = new ArrayList<>();

  public ItemDao() {}

  @Override
  public Item getById(int id) {
    return items.get(id);
  }

  @Override
  public void add(Item item) {
    items.add(item);
  }

  @Override
  public void delete(Item item) {
    items.remove(item);
  }

  @Override
  public List<Item> getAll() {
    return items;
  }

  public List<Item> getUserItems(int userId) {
    List<Item> itemList = new ArrayList<>();
    for (Item i : items) {
      if (i.getOwnerCard() == userId) {
        itemList.add(i);
      }
    }
    return itemList;
  }
}
