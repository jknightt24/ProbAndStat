public class TCGTESTER 
{
    public static void main(String[] args)
    {
        TCG game = new TCG();
        game.cardGame();
        
        game.isBricked(50000, 56, 4);
        game.isBricked(50000, 57, 3);
        game.isBricked(50000, 58, 2);
        game.isBricked(50000, 59, 1);
       // game.onePokemonCard(50000, 2);
        //game.onePokemonCard(50000, 3);
        
        
    }
}
