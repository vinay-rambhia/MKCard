package com.example.android.cardsgame;
import java.util.ArrayList;

/**
 * Represents the Table on which Cards are played.
 */
public class Table {

    private ArrayList<Card> CardsOnTable = new ArrayList();
    private int NumCardsOnTable;

    /**
     * Constructor for a Table class. Sets the Number of Cards on Table = 0
     *
     */
    public void Table()
    {
        NumCardsOnTable = 0;
    }

    /**
     * Adds a Card to the CardsOnTable Arraylist. This denotes Cards that are played by players.
     *
     * @param  Card  Card that needs to be added on List in Table.
     */
    public void AddCard(Card Card)
    {
        CardsOnTable.add(Card);
        NumCardsOnTable++;
    }

    public void RemoveCard(int CardNo)
    {
        CardsOnTable.remove(CardNo);
        NumCardsOnTable--;
    }

    public Card GetCard(int CardNo)
    {
        return CardsOnTable.get(CardNo);
    }

    public int GetNoOfCardsOnTable()
    {
        return NumCardsOnTable;
    }
}