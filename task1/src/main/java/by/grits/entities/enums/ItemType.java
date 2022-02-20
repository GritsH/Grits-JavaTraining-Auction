package by.grits.entities.enums;

public enum ItemType {
  FURNITURE(0),
  BOOK(1),
  DECOR(2),
  MUSICAL_INSTRUMENT(3),
  PAINTING(4),
  OTHER(5);
  int index;

  ItemType(int index) {
    this.index = index;
  }

  public static ItemType getByIndex(int index) {
    for (ItemType value : ItemType.values()) {
      if (value.index == index) {
        return value;
      }
    }
    return null;
  }
}
