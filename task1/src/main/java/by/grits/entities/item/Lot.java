package by.grits.entities.item;

import by.grits.entities.people.CommonUser;

import java.util.List;
import java.util.Random;

public class Lot {
    private int lotCard;
    private CommonUser host;
    private CommonUser lotLeader;
    private int startingPrice;
    private int auctionStep;
    private int currentPrice;
    private List<Item> auctionItems;

    public Lot(CommonUser host, CommonUser lotLeader, int startingPrice, int auctionStep, int currentPrice, List<Item> auctionItems) {
        this.host = host;
        this.lotLeader = lotLeader;
        this.startingPrice = startingPrice;
        this.auctionStep = auctionStep;
        this.currentPrice = currentPrice;
        this.auctionItems = auctionItems;
        Random r = new Random();
        lotCard = r.nextInt(10000) + 10000;
    }

    public CommonUser getHost() {
        return host;
    }

    public CommonUser getLotLeader() {
        return lotLeader;
    }

    public void setLotLeader(CommonUser lotLeader) {
        this.lotLeader = lotLeader;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getLotCard() {
        return lotCard;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public int getAuctionStep() {
        return auctionStep;
    }

    public List<Item> getAuctionItems() {
        return auctionItems;
    }
}
