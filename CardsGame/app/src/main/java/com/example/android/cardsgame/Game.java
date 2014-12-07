package com.example.android.cardsgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the Game.
 */
public class Game implements Serializable
{
    public final int CARDS_IN_A_DECK                    = 52;
    public final int MAX_CARDS_ON_TABLE                 = 4;
    public final int ADDITIONAL_POINTS_FOR_HUKKAM       = 20;
    public final int MAX_CARDS_IN_A_SUITE               = 13;

    private ArrayList<Player>  Players    = new ArrayList<Player>();
    private ArrayList<Card> CardDeck      = new ArrayList<Card>();
    private ArrayList<Integer> ResIds     = new ArrayList<Integer>();
    private Table table                   = new Table();
    private Team  TeamRed                 = new Team();// Team of Player 1 and Player 3
    private Team  TeamBlue                = new Team();// Team of Player 2 and Player 4

    private int CardsLeftInDeck;
    private int NoOfPlayers;
    private int PlayerTurn;

    /**
     * Constructor for a Game class. Sets the default Game variables.
     *
     */
    Game()
    {
        CardsLeftInDeck     = CARDS_IN_A_DECK;
        NoOfPlayers         = 4; //TODO: Add support for selecting number of players
        PlayerTurn          = 0;
    }

    /**
     * Get a Player from Players.
     *
     * @param  i Index of the Player.
     * @return Player at ith Index.
     */
    public Player getPlayer(int i)
    {
        return Players.get(i);
    }

    /**
     * Get Next Player from Players.
     *
     * @return Player who has to play the next turn.
     */
    public Player GetNextPlayer()
    {
        PlayerTurn = (PlayerTurn + 1 ) % NoOfPlayers;
        return (Players.get(PlayerTurn));
    }

    /**
     * Get Number of Players.
     *
     * @return NoOfPlayers.
     */
    public int GetNumPlayers()
    {
        return NoOfPlayers;
    }

    /**
     * Get Table in the Game.
     *
     * @return Table of the Current Game.
     */
    public Table getTable()
    {
        return table;
    }

    public Team GetTeam(int PlayerIdx)
    {
        if(PlayerIdx % 2 == 0)
        {
            return TeamRed;
        }
        else
        {
            return TeamBlue;
        }
    }

    /**
     * Initialize the Card Deck. Assign the ResId of the Drawable with that Card and also set Index of that Card.
     *
     */
    public void InitializeCardDeck()
    {
        int     Points = 15;
        Card    C;

        // Initialize the CardDeck with cards
        for(int i = 0; i < CARDS_IN_A_DECK; i++)
        {
            if(i % 4 == 0)
            {
                Points--;
            }
            C = new Card(ResIds.get(i), i, Points);
            CardDeck.add(C);
        }
    }

    /**
     * Initialize the Arraylist of ResId's.
     *
     */
    public void InitializeResIds()
    {
        // TODO: Optimize to use Arraylist
        ResIds.add(R.drawable.card1);
        ResIds.add(R.drawable.card2);
        ResIds.add(R.drawable.card3);
        ResIds.add(R.drawable.card4);
        ResIds.add(R.drawable.card5);
        ResIds.add(R.drawable.card6);
        ResIds.add(R.drawable.card7);
        ResIds.add(R.drawable.card8);
        ResIds.add(R.drawable.card9);
        ResIds.add(R.drawable.card10);
        ResIds.add(R.drawable.card11);
        ResIds.add(R.drawable.card12);
        ResIds.add(R.drawable.card13);
        ResIds.add(R.drawable.card14);
        ResIds.add(R.drawable.card15);
        ResIds.add(R.drawable.card16);
        ResIds.add(R.drawable.card17);
        ResIds.add(R.drawable.card18);
        ResIds.add(R.drawable.card19);
        ResIds.add(R.drawable.card20);
        ResIds.add(R.drawable.card21);
        ResIds.add(R.drawable.card22);
        ResIds.add(R.drawable.card23);
        ResIds.add(R.drawable.card24);
        ResIds.add(R.drawable.card25);
        ResIds.add(R.drawable.card26);
        ResIds.add(R.drawable.card27);
        ResIds.add(R.drawable.card28);
        ResIds.add(R.drawable.card29);
        ResIds.add(R.drawable.card30);
        ResIds.add(R.drawable.card31);
        ResIds.add(R.drawable.card32);
        ResIds.add(R.drawable.card33);
        ResIds.add(R.drawable.card34);
        ResIds.add(R.drawable.card35);
        ResIds.add(R.drawable.card36);
        ResIds.add(R.drawable.card37);
        ResIds.add(R.drawable.card38);
        ResIds.add(R.drawable.card39);
        ResIds.add(R.drawable.card40);
        ResIds.add(R.drawable.card41);
        ResIds.add(R.drawable.card42);
        ResIds.add(R.drawable.card43);
        ResIds.add(R.drawable.card44);
        ResIds.add(R.drawable.card45);
        ResIds.add(R.drawable.card46);
        ResIds.add(R.drawable.card47);
        ResIds.add(R.drawable.card48);
        ResIds.add(R.drawable.card49);
        ResIds.add(R.drawable.card50);
        ResIds.add(R.drawable.card51);
        ResIds.add(R.drawable.card52);
    }

    /**
     * Randomly Distribute Cards to one Player.
     *
     * @param player of the Current Game.
     */
    public void DistributeCardsToPlayer(Player player)
    {
        Random RandomNoGenerator = new Random();
        int NumCardsWithEachPlayer = CARDS_IN_A_DECK / NoOfPlayers;

        // At the time of Distribution player will have total of #cardsInDesk / #players     
        for(int i = 0; i < NumCardsWithEachPlayer; i++)
        {
            // Randomly select a card from the ones remaining in the Deck
            int CurrentCard = 0;
            Card card;
            if(CardsLeftInDeck > 1) // If check to avoid exception in calling nextInt()
            {
                CurrentCard = RandomNoGenerator.nextInt(CardsLeftInDeck - 1); // -1 because Cards start from 0
            }

            // Remove this card from CardDeck
            card = CardDeck.remove(CurrentCard);

            // add this card to this players list
            player.AddCard(card);

            CardsLeftInDeck--;
        }
    }

    /**
     * Randomly Distributes the cards amongst all the players.
     *
     */
    public void Shuffle()
    {
        // Initialize the Card Deck and Distribute cards to players
        InitializeCardDeck();

        for(int i = 0; i < NoOfPlayers; i++)
        {
            DistributeCardsToPlayer(Players.get(i));
        }
    }

    /**
     * Adds Players to Game.
     *
     */
    public void AddPlayers()
    {
        for(int i = 0; i < NoOfPlayers; i++)
        {
            Player  player = new Player();
            Players.add(player);
        }
    }

    public void UpdateCardPointsWithPlayerAfterHukkamValid(Player Player, int Hukkam)
    {
        Card Card;

        for(int i = 0; i < Player.getNoOfCardsInHand(); i++)
        {
            Card = Player.GetCard(i);
            if(Card.GetSuite() == Hukkam)
            {
                Card.SetPoints(Card.GetPoints() + ADDITIONAL_POINTS_FOR_HUKKAM);
            }
        }
    }

    public void UpdateCardPointsAfterHukkamValid(int Hukkam)
    {
        for(int i = 0; i < NoOfPlayers; i++)
        {
            Player  Player = Players.get(i);
            UpdateCardPointsWithPlayerAfterHukkamValid(Player, Hukkam);
        }
    }

    public Team MakeGameDecision()
    {
        if(TeamBlue.GetNoOfMendis() > TeamRed.GetNoOfMendis())
        {
            if(TeamBlue.GetNoOfMendis() == 4)
            {
                TeamRed.AddAKot();
            }

            // Team Blue Wins
            return TeamBlue;
        }
        else if(TeamBlue.GetNoOfMendis() < TeamRed.GetNoOfMendis())
        {
            if(TeamRed.GetNoOfMendis() == 4)
            {
                TeamBlue.AddAKot();
            }
            //Team Red Wins
            return TeamRed;
        }

        if(TeamBlue.GetNoOfHands() > TeamRed.GetNoOfHands())
        {
            TeamBlue.SetShuffle(false);
            TeamRed.SetShuffle(true);

            return TeamBlue;
        }
        else if(TeamBlue.GetNoOfHands() < TeamRed.GetNoOfHands())
        {
            TeamRed.SetShuffle(false);
            TeamBlue.SetShuffle(true);

            return TeamRed;
        }

        return TeamBlue;
    }
}