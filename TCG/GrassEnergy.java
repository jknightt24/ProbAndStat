public class GrassEnergy extends Energy
{
    public GrassEnergy()
    {
        setName("grass");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a grass energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
