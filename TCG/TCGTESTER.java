public class TCGTESTER 
{
    public static void main(String[] args)
    {
        TCG game = new TCG();
        game.cardGame();
        for(int i = 0; i <= 60; i++)
        {
            game.onePokemonCard(50000, i);
        }
       // game.onePokemonCard(50000, 2);
        //game.onePokemonCard(50000, 3);
        
        
    }
}
