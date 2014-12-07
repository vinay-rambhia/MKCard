package com.example.android.cardsgame;

/**
 * Represents Teams in Game.
 */
public class Team
{
    private int     HandsWithTeam;
    private int     NoOfMendis;
    private boolean IsTeamShuffle;
    private int     Kot;

    /**
     * Constructor for Teams in Game. Initializes Team Class.
     */
    Team()
    {
        HandsWithTeam = 0;
        NoOfMendis    = 0;
        IsTeamShuffle = false;
        Kot           = 0;
    }

    /**
     * Adds Mendis to This team.
     *
     * @param  NumOfMendisInThisHand  number of Mendis played in this Hand.
     */
    public void AddAMendis(int NumOfMendisInThisHand)
    {
        NoOfMendis = NoOfMendis + NumOfMendisInThisHand;
    }

    /**
     * Adds a Hand to This team.
     */
    public void AddAHand()
    {
        HandsWithTeam++;
    }

    /**
     * Gets number of hands with this team.
     *
     * @return  Hands that are with this Team.
     */
    public int GetNoOfHands()
    {
        return HandsWithTeam;
    }

    /**
     * Gets number of MEndis with this team.
     * @return   Number of Mendis with this Team
     */
    public int GetNoOfMendis()
    {
        return NoOfMendis;
    }

    /**
     * Sets if this Team has to shuffle.
     *
     * @param  IsShuffle  Is this teams shuffle.
     */
    public void SetShuffle(boolean IsShuffle)
    {
        IsTeamShuffle = IsShuffle;
    }

    /**
     * Adds a Kot to this team.
     */
    public void AddAKot()
    {
        Kot++;
    }
}
