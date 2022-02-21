package by.grits.entities.item;

import by.grits.entities.enums.ItemType;

import java.util.Objects;

public class Item {
  private String name;
  private String description;
  private String ownersEmail;
  private ItemType type;
  private int id;

  public Item(String name, String description, String ownerEmail, ItemType type) {
    this.name = name;
    this.description = description;
    this.ownersEmail = ownerEmail;
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
        + ownersEmail
        + ", type="
        + type
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return ownersEmail == item.ownersEmail
        && Objects.equals(name, item.name)
        && Objects.equals(description, item.description)
        && type == item.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, ownersEmail, type);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOwnersEmail() {
    return ownersEmail;
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
