package by.grits.entities.item;

import by.grits.entities.enums.ItemType;

import java.util.Objects;

public class Item {
    private String name;
    private String description;
    private int ownerCard;
    private ItemType type;

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ownerID=" + ownerCard +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return ownerCard == item.ownerCard && Objects.equals(name, item.name) && Objects.equals(description, item.description) && type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ownerCard, type);
    }

    public Item(String name, String description, int ownerId, ItemType type) {
        this.name = name;
        this.description = description;
        this.ownerCard = ownerId;
        this.type = type;
    }

    public Item() {

    }

    public int getOwnerCard() {
        return ownerCard;
    }

    public void setOwnerCard(int ownerCard) {
        this.ownerCard = ownerCard;
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
