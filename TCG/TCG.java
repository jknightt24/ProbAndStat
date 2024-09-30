/* Writing a simple card game
    Both players need a 60 card deck
    No more than 4 of a card, EXCEPT if it is an energy
    there are 3 types of cards, Pokemon, trainer, and energy

    At the start of the game both players draw 7 cards.

    if there are no pokemon in your hand, you have to redraw a new hand, and the opponent draws 1 for free
    After, both players take 6 cards and puts them aside. these cards are called prizes.
    each time you knock you a pokemon you can add a prize to your hand.
    You win when you collect all 6 prizes
    each player takes a turn. On their turn, they can draw, put a pokemon into play, put an energy on a pokemon, 
       play trainer cards, and attack (which ends your turn)
    To attack, your pokemon must have energy. it deals damage to opponents hp.
    there are 6 spots on each persons field. One active, 5 bench. at most you can only have 6 pokemon in play but only 1 in the front spot can attack

    */

import java.util.ArrayList;
import java.util.random;

public class TCG 
{
    private ArrayList<Card> deck
    private ArrayList<Card> hand

    public CardGame()
    {
        deck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void fillDeck()
    {
        for(int i = 0; i < 59; i++)
        {
            deck.add(new Energy());
        }

        deck.add(new Charmander());
    }

    public boolean checkHand()
    {
        for(Card singleCard : hand)
        {
            if(singleCard insatanceof Pokemon)
            {
                return true;
            }
            return false;
        }
    }

    public void drawHand()
    {
        Random rng = new Random();

        for(int i = 0; i < 7; i++)
        {
            int cardToTakeIndex = rng.nextInt(deck.size() + 1)
            hand.add(deck.get(cardToTakeIndex))
            deck.remove(cardToTakeIndex);
        }
    }

    public void run();
    {
        fillDeck();
        drawHand();
        checkHand();
    }
}
