public class FireEnergy extends Energy
{
    public FireEnergy()
    {
        setName("fire");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a fire energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
