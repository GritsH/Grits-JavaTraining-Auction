package by.grits.entities.item;

import by.grits.entities.enums.ItemType;

import java.util.Objects;

public class Item {
  private String name;
  private String description;
  private String ownersPhone;
  private ItemType type;

  public Item(String name, String description, String ownerPhone, ItemType type) {
    this.name = name;
    this.description = description;
    this.ownersPhone = ownerPhone;
    this.type = type;
  }

  @Override
  public String toString() {
    return "Item{"
        + "name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", ownersPhone="
        + ownersPhone
        + ", type="
        + type
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return ownersPhone == item.ownersPhone
        && Objects.equals(name, item.name)
        && Objects.equals(description, item.description)
        && type == item.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, ownersPhone, type);
  }

  public String getOwnersPhone() {
    return ownersPhone;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public ItemType getType() {
    return type;
  }
}
