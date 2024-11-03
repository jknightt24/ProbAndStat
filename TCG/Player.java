public class Player extends TCG
{
    private String name;

    public Player(String name)
    {
        this.name = "";    
    }

    
    public String getName()
    {
        return name;
    }

    public void setName(String userInputName)
    {
        name = userInputName;
    }
}
