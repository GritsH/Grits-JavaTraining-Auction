package by.grits.entities.people;

import by.grits.entities.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonUser extends User {
    private List<Item> items;
    private int participantCard;

    public CommonUser(String name, String phoneNumber, String emailAddress, String login,
                      String password) {
        super(name, phoneNumber, emailAddress, login, password);
        items = new ArrayList<>();
        Random r = new Random();
        participantCard = r.nextInt(10000) + 10000;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItem(Item item) {
        this.items.add(item);
    }

    public int getParticipantCard() {
        return participantCard;
    }
}
