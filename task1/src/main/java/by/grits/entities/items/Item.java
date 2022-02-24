package by.grits.entities.items;

import by.grits.entities.enums.ItemType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 * This class represents entity of item. Consists of fields that describe main properties of probably
 * any item.
 */
public class Item implements Comparable<Item>, Serializable {
  private static final long serialVersionUID = 123L;

  private Integer id;
  private String name;
  private String description;
  private String ownersEmail;
  private ItemType type;
  private Date addedAt;

  public Item(String name, String description, String ownerEmail, ItemType type) {
    this.name = name;
    this.description = description;
    this.ownersEmail = ownerEmail;
    this.type = type;
  }

  public Date getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(Date addedAt) {
    this.addedAt = addedAt;
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

  @Override
  public int compareTo(Item item) {
    int result = this.addedAt.compareTo(item.addedAt);
    if (result == 0) {
      result = this.id.compareTo(item.id);
    }
    return result;
  }
}
