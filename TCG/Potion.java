/*
 * Potion is a subclass of Trainer. It has a name and a play method that heals the target Pokemon by 30 HP.
 */
public class Potion extends Trainer
{
    public Potion()
    {
        setName("potion");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You played a potion card!");
        effect(targetPokemon);
        
        sendToDiscardPile(this);
    }

    public void effect(Pokemon targetPokemon)
    {
        System.out.println(targetPokemon.getName() + " has been healed by 30 HP!");
        targetPokemon.heal(30);
        System.out.println(targetPokemon.getName() + " now has " + targetPokemon.getHp() + " HP.");
    }
}
