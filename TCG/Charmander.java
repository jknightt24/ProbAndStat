/*
 * Charmander is a class that extends Pokemon and creates a Charmander object with the following attributes:
 * Name, HP, type, resistance, weakness, and retreat cost. It also has the following methods: Collect and Ember.
 */
public class Charmander extends Pokemon
{
    public Charmander()
    {
        setName("Charmander");
        setHp(70);
        setType("Fire");
        setResistance("None");
        setWeakness("Water");
        setRetreatCost(1, "Basic");
    }

    public void Collect()
    {
        if(getEnergyAmount().contains("Fire"))
        {
            getEnergyAmount().remove("Fire");
            drawCard(1);
        }
        else
        {
            System.out.println("Not enough energy to use this attack");
        }
    }

    public void Ember(Pokemon enemy)
    {
        if(getEnergyAmount().contains("Fire"))
        {
            getEnergyAmount().remove("Fire");
            attack2(enemy, 30);
        }
        else
        {
            System.out.println("Not enough energy to use this attack");
        }
    }
}
