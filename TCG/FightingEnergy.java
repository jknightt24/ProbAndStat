public class FightingEnergy extends Energy
{
    public FightingEnergy()
    {
        setName("Fighting");
    }
    
    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a fighting energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
