public class BasicEnergy extends Energy
{
    public BasicEnergy()
    {
        setName("Basic");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a basic energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
