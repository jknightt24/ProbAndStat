public class PsychicEnergy extends Energy
{
    public PsychicEnergy()
    {
        setName("Psychic");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a psychic energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
