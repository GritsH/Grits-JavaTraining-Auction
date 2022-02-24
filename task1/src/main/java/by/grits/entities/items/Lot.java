package by.grits.entities.items;

import java.util.Date;

public class Lot {
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
