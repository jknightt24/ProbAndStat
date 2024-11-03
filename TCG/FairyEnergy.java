public class FairyEnergy extends Energy
{
    public FairyEnergy()
    {
        setName("Fairy");
    }    

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You applied a fairy energy card to " + targetPokemon.getName());
        targetPokemon.addEnergy(this);
    }
}
