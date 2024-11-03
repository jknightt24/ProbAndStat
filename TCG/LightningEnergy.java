public class LightningEnergy extends Energy 
{
    public LightningEnergy()
    {
        setName("Lightning");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a lightning energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
