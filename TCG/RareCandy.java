public class RareCandy extends Trainer
{
    public void rareCandy()
    {
        setName("rareCandy");
    }

    @Override
    public void play(Pokemon targetPokemon)
    {
        System.out.println("You played a rare candy card on " + targetPokemon.getName());
        System.out.println("it does nothing lmao but it's a rare candy so it's cool");
    }
}
