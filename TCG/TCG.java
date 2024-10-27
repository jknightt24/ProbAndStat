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
import java.util.Random;


public class TCG 
{
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizeCards;
    private double count;

    public void cardGame()
    {
        deck = new ArrayList<>(60);
        hand = new ArrayList<>(7);
        prizeCards = new ArrayList<>(6);
        
    }

    public void fillDeck(int amountOfPokemon, int amountOfTrainer)
    {
        for(int i = 0; i < amountOfPokemon; i++)
        {
            deck.add(new Charmander());
        }

        for(int i = 0; i < 60 - amountOfPokemon; i++)
        {
            deck.add(new Energy());
        }

        for(int i = 0; i < amountOfTrainer; i++)
        {
            deck.add(new RareCandy());
        }
    }

    public void setPrizedCards()
    {
        Random rng = new Random();
      
        while(prizeCards.size() < 6)
        {
            int cardToTakeIndex = rng.nextInt(deck.size());
            prizeCards.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
        }
    }

    public boolean checkHand()
    {
        for(Card singleCard : hand)
        {
            if(singleCard instanceof Pokemon)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkDeck()
    {
        for(Card singleCard : deck)
        {
            if(singleCard instanceof Trainer)
            {
                return true;
            }
        }
        return false;
    }

    public void drawHand()
    {
        Random rng = new Random();
      
        while(hand.size() < 7)
        {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
        }
    }

    public void clear()
    {
        deck.clear();
        hand.clear();
        prizeCards.clear();
    }

    

    public double onePokemonCardOdds(double numberOfRuns, int amountOfPokemon, int amountOfTrainer)
    {
        count = 0;

        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                count += 1;
                run(amountOfPokemon, amountOfTrainer); 
            }
            else
            {
                run(amountOfPokemon, amountOfTrainer);
            }
        }
        System.out.println((count / numberOfRuns) * 100 + "%");
        return (count / numberOfRuns) * 100;
    }

    public double isBricked(double numberOfRuns, int amountOfPokemon, int amountOfTrainer)
    {
        count = 0;

        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                setPrizedCards();

                if(checkDeck() == true)
                {
                    run(amountOfPokemon, amountOfTrainer);
                }
                else
                {
                    count += 1;
                    run(amountOfPokemon, amountOfTrainer);
                }
            }
            else
            {
                run(amountOfPokemon, amountOfTrainer);
            }
        }
        System.out.println((count / numberOfRuns) * 100 + "%");
        return (count / numberOfRuns) * 100;

    }

    public void run(int amountOfPokemon, int amountOfTrainer)
    {
        cardGame();
        fillDeck(amountOfPokemon, amountOfTrainer);
        drawHand();
    }
    /* 
    public double twoPokemonCard(int numberOfRuns)
    {
        count = 0;

        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                count += 1;
                run(2); 
            }
            else
            {
                run(2);
            }
        }
        System.out.println((count / numberOfRuns) * 100 + "%");
        return (count / numberOfRuns) * 100;
    }

    public double threePokemonCard(int numberOfRuns)
    {
        count = 0;

        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                count += 1;
                run(3); 
            }
            else
            {
                run(3);
            }
        }
        System.out.println((count / numberOfRuns) * 100 + "%");
        return (count / numberOfRuns) * 100;
    }
        */
        
}
