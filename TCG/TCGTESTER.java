public class TCGTESTER 
{
    public static void main(String[] args)
    {
        TCG game = new TCG();
        game.cardGame();

        //game.onePokemonCardOdds(50000, 1, 0);
        game.onePokemonCardOdds(50000, 1, 0);
        game.onePokemonCardOdds(50000, 2, 0);
        game.onePokemonCardOdds(50000, 3, 0);
        game.onePokemonCardOdds(50000, 4, 0);

        game.isBricked(50000, 56);
        game.isBricked(50000, 57);
        game.isBricked(50000, 58);
        game.isBricked(50000, 59);

        //game.isBricked(50000, 57, 3);
        //game.isBricked(50000, 58, 2);
        //game.isBricked(50000, 59, 1);
       // game.onePokemonCard(50000, 2);
        //game.onePokemonCard(50000, 3);
        
        //game.setBoard(30, 15);
        
    }
}
