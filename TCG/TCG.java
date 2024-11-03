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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TCG 
{
    private int count;
    private ArrayList<Card> deck;
    private LinkedList<Card> hand;
    private ArrayList<Card> prizeCards;
    private LinkedList<Card> discardPile;
    private LinkedList<Pokemon> bench;
    private LinkedList<Pokemon> activePokemon;
    private Player player1;
    private Player player2;

    /**
     * Constructor for objects of class TCG
     * Initializes the deck, hand, prizeCards, discardPile, bench, and activePokemon
     */
    public void cardGame()
    {
        deck = new ArrayList<>(60);
        hand = new LinkedList<>();
        prizeCards = new ArrayList<>(6);
        discardPile = new LinkedList<>();
        bench = new LinkedList<>();
        activePokemon = new LinkedList<>();
    }

    /**
     * Calls cardGame() to initialize the deck, hand, prizeCards, discardPile, bench, and activePokemon
     * Fills the deck with the amount of pokemon and energy cards specified
     * Draws 7 cards from the deck and adds them to the hand
     * If the starting hand doesnt contain a single pokemon card, it will put the cards back in the deck and draw a new hand
     * Sets the prize cards by randomly taking 6 cards from the deck and adding them to the prizeCards arraylist
     * @param amountOfPokemon
     * @param amountOfEnergy
     */
    public void setBoard(int amountOfPokemon, int amountOfEnergy)
    {
        cardGame();
        fillDeck(amountOfPokemon, amountOfEnergy);
        drawHand();
        if(checkHand() == false)
        {
            mulligan();
            count++;
        }
        setPrizedCards();
    }

    /**
     * Simulates the card game
     * The player draws a card, plays a card, and ends their turn
     */
    public void turnStart()
    {
        int count = 0;

        if(count == 0)
        {
            setBoard(20, 20);
            count++;
        }
        else
        {
            drawCard(1);
        }
        
        
        showHand();
        if(activePokemon.isEmpty())
        {
            Scanner scan = new Scanner(System.in);

            System.out.println("Please put one of your pokemon in hand into the active spot");
            String input = scan.nextLine();

            Iterator<Card> iterator = hand.iterator();
            while (iterator.hasNext()) 
            {
                Card singleCard = iterator.next();
                if(singleCard.getName().equalsIgnoreCase(input)) 
                {
                    if(singleCard instanceof Pokemon)
                    {
                        activePokemon.add((Pokemon)singleCard);
                        iterator.remove();
                        showBoard();
                        showHand();
                        break;
                    }
                    else
                    {
                        throw new IllegalArgumentException("You must put a pokemon card in the active spot");
                    }
                }
                
            }
        }
        

        Scanner scan = new Scanner(System.in);
        System.out.println("How would you like to play the rest of your turn? ");
        System.out.println("Also you can see your board state by typing "+ "show board " + " or attack ");
        String input = scan.nextLine();
        while(!input.equalsIgnoreCase("end turn"))
        {
            if(input.equalsIgnoreCase("show board"))
            {
                this.showBoard();
                input = scan.nextLine();
            }
            else if(input.equalsIgnoreCase("attack"))
            {
                System.out.println("Which attack would you like to use? ");
                System.out.println("Attack 1 or Attack 2");
                input = scan.nextLine();

                if(input.equalsIgnoreCase("attack 1"))
                {
                    activePokemon.get(0).attack1(activePokemon.get(0));
                    this.showBoard();
                    input = scan.nextLine();
                }
                else if(input.equalsIgnoreCase("attack 2"))
                {
                    activePokemon.get(0).attack2(activePokemon.get(0));
                    showBoard();
                    input = scan.nextLine();
                }
            }

            Iterator<Card> iterator = hand.iterator();
            while(iterator.hasNext())
            {
                Card singleCard = iterator.next();
                
                if(singleCard.getName().equalsIgnoreCase(input))
                {
                    if(singleCard instanceof Pokemon)
                    {
                        bench.add((Pokemon)singleCard);
                        iterator.remove();
                        showBoard();
                        showHand();
                        input = scan.nextLine();
                    }
                    else if(singleCard instanceof Energy)
                    {
                        System.out.println("which pokemon would you like to attach this energy to?");
                        String pokemonName = scan.nextLine();

                        for(Pokemon singlePokemon : activePokemon)
                        {
                            if(singlePokemon.getName().equalsIgnoreCase(pokemonName))
                            {
                                singleCard.play(singlePokemon);
                                iterator.remove();
                                
                                activePokemon.get(0).getEnergyAmount();
                                
                                showBoard();
                                showHand();

                                System.out.println("How would you like to play the rest of your turn? ");
                                input = scan.nextLine();
                            }
                        }
                        
                    }
                    else if(singleCard instanceof Trainer)
                    {
                        singleCard.play(activePokemon.get(0));
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        System.out.println("End of turn");
        
    }

    public void startBattle()
    {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        player1.setBoard(20,20);
        player2.setBoard(20,20);

        Random rng = new Random();
        while(player1.getPrizeCards() > 0 && player2.getPrizeCards() > 0)
        {
            if(rng.nextInt(2) == 0)
            {
                System.out.println("Player 1's turn");
                player1.turnStart();
                System.out.println("Player 2's turn");
                player2.turnStart();
            }
            else
            {
                System.out.println("Player 2's turn");
                player2.turnStart();
                System.out.println("Player 1's turn");
                player1.turnStart();
                
            }
        }
        if(player1.getPrizeCards() == 0)
        {
            System.out.println("Player 2 wins!");
        }
        else if(player2.getPrizeCards() == 0)
        {
            System.out.println("Player 1 wins!");
        }
    }

    /**
     * Fills the deck with the amount of pokemon and energy cards specified
     * @param amountOfPokemon
     * @param amountOfEnergy
     */
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

    /**
     * Fills the deck with the amount of pokemon cards specified and the rest of the deck with potions
     * This is to help simulate the odds of a bricked hand
     * @param amountOfPokemon
     */
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

    /**
     * Draws the amount of cards specified from the deck randomly and add them to the hand
     * @param cards
     */
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

    /**
     * Sets the prize cards by randomly taking 6 cards from the deck and adding them to the prizeCards arraylist
     */
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

    public int getPrizeCards()
    {
        int count = 0;
        for(Card singleCard : prizeCards)
        {
            count++;
        }
        return count;
    }

    /**
     * Checks if there is a pokemon card in the hand
     * @return true if there is a pokemon card in the hand and false if there is not
     */
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

    /**
     * Checks if there is a trainer card in the hand
     * @return true if there is a trainer card in the hand and false if there is not
     */
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

    /**
     * Checks if there is a pokemon card in the deck
     * @return true if there is a pokemon card in the deck and false if there is not
     */
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

    /**
     * Checks if there is a trainer card in the deck
     * @return true if there is a trainer card in the deck and false if there is not
     */
    public boolean checkDeckTrainer()
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

    /**
     * Draws 7 cards from the deck and adds them to the hand
     * If the starting hand doesnt contain a single pok
     */
    public void drawHand()
    {
        Random rng = new Random();
      
        while(hand.size() < 7)
        {
            int cardToTakeIndex = rng.nextInt(deck.size());
            hand.add(deck.get(cardToTakeIndex));
            deck.remove(cardToTakeIndex);
        }

        if(checkHand() == false)
        {
            Iterator<Card> iterator = hand.iterator();
            while(iterator.hasNext())
            {
                Card singleCard = iterator.next();
                while(hand.size() != 0)
                {
                    deck.add(singleCard);
                    iterator.remove();
                }
            }

            drawHand();
        }
    }

    /**
     * Clears the deck, hand, and prizeCards arraylists
     */
    public void clear()
    {
        deck.clear();
        hand.clear();
        prizeCards.clear();
    }

    

    /**
     * Caclulates the odds of drawing a pokemon card in your hand given the amount of pokemon cards in the deck
     * in comparison to the amount of trainer cards in the deck
     * @param numberOfRuns
     * @param amountOfPokemon
     * @param amountOfTrainer
     * @return the odds of drawing a pokemon card in your hand
     */
    public double onePokemonCardOdds(double numberOfRuns, int amountOfPokemon, int amountOfTrainer)
    {
        double count = 0;

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

    /**
     * Caclulates the odds of having a bricked deck after drawing a hand with a pokemon in it and setting prized cards.
     * The deck is bricked if there are no more trainer cards in your hand and deck
     * @param numberOfRuns
     * @param amountOfPokemon
     * @return the odds of having a bricked deck
     */
    public double isBricked(double numberOfRuns, int amountOfPokemon)
    {
        double count = 0;
        runIsBricked(amountOfPokemon);

        for(int i = 0; i < numberOfRuns; i++)
        {
            if(checkHand() == true)
            {
                if(checkHandTrainer() == true || checkDeckTrainer() == true)
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
                runIsBricked(amountOfPokemon);
            }
        }
        System.out.println((count / numberOfRuns) * 100 + "%");
        return (count / numberOfRuns) * 100;
    }

    /**
     * Runs the card game with the amount of pokemon and trainer cards specified
     * used to determine the odds of having a pokemon card in your hand
     * @param amountOfPokemon
     * @param amountOfTrainer
     */
    public void run(int amountOfPokemon, int amountOfTrainer)
    {
        cardGame();
        fillDeck(amountOfPokemon, amountOfTrainer);
        drawHand();
    }

    /**
     * Runs the card game with the amount of pokemon cards specified
     * used to determine the odds of having a bricked deck
     * @param amountOfPokemon
     */
    public void runIsBricked(int amountOfPokemon)
    {
        cardGame();
        fillDeckIsBricked(amountOfPokemon);
        drawHand();
        setPrizedCards();
    }

    /**
     * Adds a card to the arraylist specified
     * can be used to add a card to the deck, hand, prizeCards, or discardPile
     * @param card
     * @param list
     */
    public void add(Card card, ArrayList<Card> list)
    {
        list.add(card);
    }

    /**
     * @return the arraylist containing the card in the active spot
     */
    public LinkedList<Pokemon> getActivePokemon()
    {
        return activePokemon;
    }

    /**
     * sets a specified card in the active spot
     * @param thisPokemon
     */
    public void setActivePokemon(LinkedList<Pokemon> thisPokemon)
    {
        activePokemon = thisPokemon;
    }

    /**
     * @return the arraylist containing the cards in the bench
     */
    public LinkedList<Pokemon> getBench()
    {
        return bench;
    }

    /**
     * puts a specified pokemon card in the bench
     * @param addedPokemon
     */
    public void setBench(Pokemon addedPokemon)
    {
        bench.add(addedPokemon);
    }

    /**
     * @return the arraylist containing the discardPile
     */
    public LinkedList<Card> getDiscardPile()
    {
        return discardPile;
    }

    /**
     * puts a specified card in the discard pile
     * @param addedCard
     */
    public void setDiscardPile(Card addedCard)
    {
        discardPile.add(addedCard);
    }

    public void showHand()
    {
        System.out.println("-----------------");
        for(Card singleCard : hand)
        {
            System.out.println(singleCard.getName());
        }
        System.out.println("-----------------");
    }

    public void showBoard()
    {
        System.out.println("-----------------");
        System.out.println("Active Pokemon: ");
        System.out.println(activePokemon.get(0).getName());
        if(activePokemon.get(0).getEnergyAmount().size() > 0)
        {
            System.out.println("Energy: ");
            for(Energy singleEnergy : activePokemon.get(0).getEnergyAmount())
            {
                System.out.println(singleEnergy.getName());
            }
        }

        System.out.println("Bench: ");
        for(Pokemon singlePokemon : bench)
        {
            System.out.println(singlePokemon.getName());
        }
        for(Pokemon singlePokemon : bench)
        {
            if(singlePokemon.getEnergyAmount().size() > 0)
            {
                System.out.println("Energy: ");
                for(Energy singleEnergy : singlePokemon.getEnergyAmount())
                {
                    System.out.println(singleEnergy.getName());
                }
            }
        }

        System.out.println("Prize Cards: ");
        System.out.println(prizeCards.size());

        System.out.println("Discard Pile: ");
        for(Card singleCard : discardPile)
        {
            System.out.println(singleCard.getName());
        }
        System.out.println("-----------------");
    }

    public void mulligan()
    {
        if(checkHand() == false)
        {
            Iterator<Card> iterator = hand.iterator();
            while(iterator.hasNext())
            {
                Card singleCard = iterator.next();
                while(hand.size() != 0)
                {
                    deck.add(singleCard);
                    iterator.remove();
                }
            }
        }
    }

    
    
}
