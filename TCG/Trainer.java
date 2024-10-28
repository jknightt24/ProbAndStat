/**
 * Trainer is a subclass of Card. It has a name field and a getName and setName method.
 */
public abstract class Trainer extends Card 
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String userInputName)
    {
        this.name = userInputName;
    }
}
