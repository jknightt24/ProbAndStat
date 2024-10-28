/**
 * Card class is a subclass of TCG class. It has a name attribute and a play method.
 */
public abstract class Card extends TCG
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

    public void Play(Card card)
    {
        System.out.println("Card played");
    }
}
