/**
 * Eenergy is a subclass of Card that represents the energy cards in the game.
 * It has a name attribute
 */
public abstract class Energy extends Card
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String userInputName)
    {
        name = userInputName;
    } 
}
