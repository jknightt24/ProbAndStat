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
import java.util.Scanner;


public class TCG 
{
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> prizeCards;
    private ArrayList<Card> discardPile;
    private ArrayList<Pokemon> bench;
    private ArrayList<Card> activePokemon;



    public void cardGame()
    {
        deck = new ArrayList<>(60);
        hand = new ArrayList<>(7);
        prizeCards = new ArrayList<>(6);
        discardPile = new ArrayList<>(60);
        bench = new ArrayList<>(5);
        activePokemon = new ArrayList<>(1);
    }

    public void turnStart()
    {
        drawCard(1);

        Scanner scan = new Scanner(System.in);

        System.out.println("Please type in the name of the card you want to play. ");
        String cardName = scan.nextLine();

        for(Card singleCard : hand)
        {
            if(singleCard.getName().equals(cardName))
            {
                if(singleCard instanceof Pokemon)
                {
                    System.out.println("would you like to put this pokemon in the active spot or the bench?");
                    String spot = scan.nextLine();

                    if(spot.equals("active"))
                    {
                        activePokemon.add(singleCard);
                        hand.remove(singleCard);
                    }
                    else if(spot.equals("bench"))
                    {
                        bench.add((Pokemon)singleCard);
                        hand.remove(singleCard);
                    }
                }
                else if(singleCard instanceof Energy)
                {
                    System.out.println("which pokemon would you like to attach this energy to?");
                    String pokemonName = scan.nextLine();

                    for(Pokemon singlePokemon : bench)
                    {
                        if(singlePokemon.getName().equals(pokemonName) && singlePokemon instanceof Pokemon)
                        {
                            singlePokemon.addEnergy((Energy)singleCard);
                            hand.remove(singleCard);
                        }
                    }
                }
                else if(singleCard instanceof Trainer)
                {
                    singleCard.play(activePokemon.get(0));
                    hand.remove(singleCard);
                }
            }
        }
    }

    public void fillDeck(int amountOfPokemon, int amountOfEnergy)
    {
        for(int i = 0; i < amountOfPokemon; i++)
        {
            deck.add(new Charmander());
        }

        for(int i = 0; i < amountOfEnergy; i++)
        {
            deck.add(new FireEnergy());
        }

        for(int i = 0; i < 60 - (amountOfPokemon + amountOfEnergy); i++)
        {
            deck.add(new Potion());
        }
    }

    public void fillDeckIsBricked(int amountOfPokemon)
    {
        for(int i = 0; i < amountOfPokemon; i++)
        {
            deck.add(new Charmander());
        }

        for(int i = 0; i < 60 - amountOfPokemon; i++)
        {
            deck.add(new Potion());
        }
    }

    public void drawCard(int cards)
    {
        Random rng = new Random();
        for(int i = 0; i < cards; i++)
        {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
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

    public boolean checkHandTrainer()
    {
        for(Card singleCard : hand)
        {
            if(singleCard instanceof Trainer)
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
            System.out.println(singleCard.getName());
            if(singleCard instanceof Pokemon)
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

    public void setBoard(int amountOfPokemon, int amountOfEnergy)
    {
        cardGame();
        fillDeck(amountOfPokemon, amountOfEnergy);
        drawHand();

        for(Card singleCard : hand)
        {
            System.out.print(singleCard.getName() + " ");
        }
    }

    

    public double onePokemonCardOdds(double numberOfRuns, int amountOfPokemon, int amountOfTrainer)
    {
        double count = 0;

        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                run(amountOfPokemon, amountOfTrainer); 
            }
            else
            {
                count += 1; 
                run(amountOfPokemon, amountOfTrainer);
            }
        }
        System.out.println((count / numberOfRuns) * 100 + "%");
        return (count / numberOfRuns) * 100;
    }

    public double isBricked(double numberOfRuns, int amountOfPokemon)
    {
        double count = 0;
        runIsBricked(amountOfPokemon);

        System.out.println(deck);
        System.out.println(hand);
        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                if(checkHandTrainer() == true || checkDeck() == true)
                {
                    runIsBricked(amountOfPokemon); 
                }
                else
                {
                    count += 1; 
                    runIsBricked(amountOfPokemon);
                }
            }
            else
            {
                count += 1; 
                runIsBricked(amountOfPokemon);
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

    public void runIsBricked(int amountOfPokemon)
    {
        cardGame();
        fillDeckIsBricked(amountOfPokemon);
        drawHand();
    }

    public void add(Card card, ArrayList<Card> list)
    {
        list.add(card);
    }

    public ArrayList<Card> getActivePokemon()
    {
        return activePokemon;
    }

    public void setActivePokemon(ArrayList<Card> thisPokemon)
    {
        activePokemon = thisPokemon;
    }

    public ArrayList<Card> getBench()
    {
        return bench;
    }

    public void setBench(Card addedPokemon)
    {
        bench.add(addedPokemon);
    }

    public ArrayList<Card> getDiscardPile()
    {
        return discardPile;
    }

    public void setDiscardPile(Card addedCard)
    {
        discardPile.add(addedCard);
    }
}
