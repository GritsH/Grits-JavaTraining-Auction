package by.grits.entities.vegetables;

public class Vegetable {

    private final int weight;
    private final int calories;

    public Vegetable(int weight, int calories) {
        this.weight = weight;
        this.calories = calories;
    }

    public int getWeight() {
        return weight;
    }

    public int getCalories() {
        return calories;
    }
}
