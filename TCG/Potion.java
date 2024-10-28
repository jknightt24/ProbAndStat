/*
 * Potion is a subclass of Trainer. It has a name and a play method that heals the target Pokemon by 30 HP.
 */
public class Potion extends Trainer
{
    public Potion()
    {
        setName("potion");
    }

    public void play(Pokemon targetPokemon)
    {
        System.out.println("You played a potion card!");
        effect(targetPokemon);
        
        getDiscardPile().add(this);
    }

    public void effect(Pokemon targetPokemon)
    {
        targetPokemon.heal(30);
    }
}
