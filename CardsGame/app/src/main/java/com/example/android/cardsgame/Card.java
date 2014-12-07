package com.example.android.cardsgame;

/**
 * Represents a single Card from a deck of Cards.
 */
public class Card
{
    private int ResId;
    private int Index;
    private int Points;
    private int Suite;

    /**
     * Constructor for a Card class. Sets the Android Resource Id for the card face and
     * Index into the Card Deck
     *
     * @param  Id  an absolute URL giving the base location of the image
     * @param  Idx the location of the image, relative to the url argument
     */
    Card(int Id, int Idx, int Pts)
    {
        ResId  = Id;
        Index  = Idx;
        Points = Pts;
        Suite  = Idx % 4;
    }

    /**
     * Get the Resource Id of the Android Drawable Resource associated with this Card.
     *
     * @return Returns the Resource Id of the Android Drawable Resource associated with this Card.
     */
    public int GetResId()
    {
        return ResId;
    }

    /**
     * Get the Index into the Card Deck for this Card.
     *
     * @return Returns the Index into the Card Deck for this Card.
     */
    public int GetIndex()
    {
        return Index;
    }

    public int GetSuite()
    {
        return Suite;
    }

    public int GetPoints()
    {
        return Points;
    }

    public void SetPoints(int Pts)
    {
        Points = Pts;
    }

    /**
     * Sets the Resource Id of this Card with the Id mentioned in parameter.
     * This card will now show the Drawable corresponding to the set Id.
     *
     * @param Id    Resource Id of the Android Drawable Resource to be set for this Card.
     */
    public void SetResId(int Id)
    {
        ResId = Id;
    }

    /**
     * Sets the Index of this Card in the Card Deck with the Index mentioned in parameter.
     *
     * @param Idx    Index of this Card in the Card Deck.
     */
    public void SetIndex(int Idx)
    {
        Index = Idx;
    }

    /**
     * Sets the Index of this Card in the Card Deck with the Index mentioned in parameter
     * and Sets the Resource Id of this Card with the Id mentioned in parameter.
     *
     * @param Id    Resource Id of the Android Drawable Resource to be set for this Card.
     * @param Idx   Index of this Card in the Card Deck.
     */
    public void SetCard(int Id, int Idx)
    {
        SetResId(Id);
        SetIndex(Idx);
    }
}
