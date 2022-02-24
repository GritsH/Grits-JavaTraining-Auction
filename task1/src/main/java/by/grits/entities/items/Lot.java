package by.grits.entities.items;

import java.io.Serializable;
/**
 * Representation of lot in auction.
 * Basically, lot - event with item which is provided for sailing. Owner of
 * this item becomes a host of the lot and puts a starting price and step (amount of money by which
 * the price rises). Different users can take part in this event and can rise the price. User who
 * offers the highest price becomes a lot leader. If the price does not change within a certain
 * time, the lot leader becomes an owner of the lot item.
 */
public class Lot implements Serializable {
  private int id;
  private String lotLeaderId;
  private int startingPrice;
  private int auctionStep;
  private int currentPrice;
  private int itemId;

  public Lot(int id, int startingPrice, int auctionStep, int itemId) {
    this.id = id;
    this.startingPrice = startingPrice;
    this.auctionStep = auctionStep;
    this.itemId = itemId;
  }

  public int getItemId() {
    return itemId;
  }

  public String getLotLeaderId() {
    return lotLeaderId;
  }

  public void setLotLeaderId(String lotLeaderId) {

    this.lotLeaderId = lotLeaderId;
  }

  public int getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(int currentPrice) {
    this.currentPrice = currentPrice;
  }

  public int getId() {
    return id;
  }

  public int getStartingPrice() {
    return startingPrice;
  }

  public int getAuctionStep() {
    return auctionStep;
  }
}
