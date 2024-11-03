public class MetalEnergy extends Energy
{
    public MetalEnergy()
    {
        setName("Metal");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a metal energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
