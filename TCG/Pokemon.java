import java.util.ArrayList;

public class Pokemon extends Card implements Moves
{
     private String name;
     private int hp;
     private int retreatCost;
     private String weakness;
     private String resistance;
     private String type;
     private ArrayList<Energy> energyAmount;
     private int attack1Cost;
     private int attack2Cost;


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

    public void attack1(Pokemon target)
    {
        int damage = 30;
        if(this.getType().equals(target.getWeakness()))
        {
            target.setHp(target.getHp() - (damage * 2));
        }
        else
        {
            target.setHp(target.getHp() - damage);
        }
    }

    public boolean canAttack1()
    {
        if(energyAmount.size() >= attack1Cost)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void attack2(Pokemon target)
    {
        int damage = 30;
        if(this.getType().equals(target.getWeakness()))
        {
            target.setHp(target.getHp() - (damage * 2));
        }
        else
        {
            target.setHp(target.getHp() - damage);
        }
    }

    public boolean canAttack2()
    {
        if(energyAmount.size() >= attack2Cost)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void getAttackCost()
    {
        System.out.println("Attack 1 cost: " + attack1Cost);
        System.out.println("Attack 2 cost: " + attack2Cost);
    }

    public void setAttackCost(int attack1, int attack2)
    {
        attack1Cost = attack1;
        attack2Cost = attack2;
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

    

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You played a " + getName() + " card!");
        getActivePokemon().add(this);
    }
}