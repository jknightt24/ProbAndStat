import java.util.ArrayList;

public class Pokemon extends Card
{
     private String name;
     private int hp;
     private int retreatCost;
     private String weakness;
     private String resistance;
     private String type;
     private ArrayList<Energy> energyAmount;


        public Pokemon()
        {
            name = "";
            hp = 0;
            retreatCost = 0;
            weakness = "";
            resistance = "";
            type = "";
            energyAmount = new ArrayList<Energy>();
        }

        public String getName()
        {
            return name;
        }

        public void setName(String userInputName)
        {
            name = userInputName;
        }
     public int getHp()
     {
         return hp;
     }

     public void setHp(int userInputHp)
     {
         hp = userInputHp;
     }

     public int getRetreatCost()
     {
         return retreatCost;
     }

     public void setRetreatCost(int userInputRetreatCost, String userEnergyType)
     {
         retreatCost = userInputRetreatCost;
     }

     public String getWeakness()
     {
         return weakness;
     }

     public void setWeakness(String userInputWeakness)
     {
        weakness = userInputWeakness;
     }

     public String getResistance()
    {
        return resistance;
    }

     public void setResistance(String userInputResistance)
    {
        resistance = userInputResistance;
    }

     public String getType()
    {
        return type;
    }

     public void setType(String userInputType)
    {
        type = userInputType;
    }

    public ArrayList<Energy> getEnergyAmount()
    {
        return energyAmount;
    }

    public void setEnergyAmount(ArrayList<Energy> userInputEnergyAmount)
    {
        energyAmount = userInputEnergyAmount;
    }

    public void addEnergy(Energy energy)
    {
        energyAmount.add(energy);
    }

    public void removeEnergy(Energy energy)
    {
        energyAmount.remove(energy);
    }

    public void addEnergyToPokemon(Energy energy)
    {
        energyAmount.add(energy);
    }

    public void attack1(Pokemon target, int damage)
    {
        if(this.getType().equals(target.getWeakness()))
        {
            target.setHp(target.getHp() - (damage * 2));
        }
        else
        {
            target.setHp(target.getHp() - damage);
        }
    }

    public void attack2(Pokemon target, int damage)
    {
        if(this.getType().equals(target.getWeakness()))
        {
            target.setHp(target.getHp() - (damage * 2));
        }
        else
        {
            target.setHp(target.getHp() - damage);
        }
    }

    public void pokemonKnockedOut(Pokemon thisPokemon)
    {
        getActivePokemon().remove(thisPokemon);
        getDiscardPile().add(thisPokemon);
        
        if(thisPokemon.getEnergyAmount().size() > 0)
        {
            for(Energy energy : thisPokemon.getEnergyAmount())
            {
                getDiscardPile().add(energy);
            }
        }
    }

    public void retreat(Pokemon thisPokemon, Energy cost)
    {
        getActivePokemon().remove(thisPokemon);
        getBench().add(thisPokemon);
    }

    public void heal(int amount)
    {
        setHp(getHp() + amount);
    }
}