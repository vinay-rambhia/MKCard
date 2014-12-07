package com.example.android.cardsgame;

import java.util.ArrayList;

/**
 * Represents a Player of the Game.
 */
public class Player {

    public ArrayList<Card>  CardsInHand    = new ArrayList<Card>();
    private ArrayList<Card>  CardsFromHands = new ArrayList<Card>(); // Stores cards that are won in the Hand.
    private String           Name;
    private int              NoOfCardsInHand;

    /**
     * Constructor for Player Class. Initializes NoOfCardsInHand = 0 and Name of Player as ""
     */
    Player()
    {
        NoOfCardsInHand = 0;
        Name            = "";
    }

    /**
     * Adds a Card to the CardsInHand Arraylist. This denotes Cards that are with that player.
     *
     * @param  card Card which has to be added to CardsInHand.
     */
    public void AddCard(Card card)
    {
        CardsInHand.add(card);
        NoOfCardsInHand++;
    }

    public int getNoOfCardsInHand()
    {
        return NoOfCardsInHand;
    }

    /**
     * Get a Card from CardsInHand.
     *
     * @param  card Index of the Card in Hand.
     * @return Card at Index.
     */
    public Card GetCard(int card)
    {
        return CardsInHand.get(card);
    }

    /**
     * Removes a Card to the CardsInHand Arraylist.
     *
     * @param  CardNo Card Index which has to be removed from CardsInHand.
     */
    public void RemoveCard(int CardNo)
    {
        CardsInHand.remove(CardNo);
        NoOfCardsInHand--;
    }

    /**
     * Sets the name from EditText.
     *
     * @param  PlayerName Name read from EditText.
     */
    public void SetName(String  PlayerName)
    {
        Name = PlayerName;
    }

    /**
     * Gets the name of the Player.
     */
    public String GetName()
    {
        return Name;
    }

    public void AddCardsFromHands(Table table)
    {
        Card card;

        for(int i = 0; i < table.GetNoOfCardsOnTable(); i++)
        {
            card = table.GetCard(i);
            CardsFromHands.add(card);
        }
    }

    // Not used... may be not required
    public void Play()
    {

    }
}