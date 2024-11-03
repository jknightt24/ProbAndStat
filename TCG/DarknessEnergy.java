public class DarknessEnergy extends Energy
{
    public DarknessEnergy()
    {
        setName("Darkness");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a darkness energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
