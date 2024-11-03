public class WaterEnergy extends Energy
{
    public WaterEnergy()
    {
        setName("Water");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a water energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
