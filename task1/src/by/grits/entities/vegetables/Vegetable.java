package by.grits.entities.vegetables;

public class Vegetable
{
    private int Weight;
    private int Calories;

    public Vegetable(int weight, int calories)
    {
        Weight = weight;
        Calories = calories;
    }

    public int getWeight()
    {
        return Weight;
    }

    public int getCalories()
    {
        return Calories;
    }
}
