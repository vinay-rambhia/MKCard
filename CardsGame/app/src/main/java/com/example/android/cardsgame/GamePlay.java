package com.example.android.cardsgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlay extends Activity {

    //private ArrayList<ImageView>   PlayerCard = new ArrayList<ImageView>(); // Arraylist of ImageViews representing Player's CardsInHead.

    private ImageView   TableCard1;         // ImageView to represent card first player plays.
    private ImageView   TableCard2;         // ImageView to represent card second player plays.
    private ImageView   TableCard3;         // ImageView to represent card third player plays.
    private ImageView   TableCard4;         // ImageView to represent card fourth player plays.

    private ImageView   TableCard;          // ImageView to hold current card on table.

    private TextView    PlayerName;         // TextView to store Player's name.

    private GridView    PlayerCardsGrid;

    private int         CardSelected;       // Card that the Player has selected out out of cards in hand.
    private Game        CardGame;           // Instance of the current Game.
    private Player      CurrentPlayer;      // Instance of Current Player.
    private int         CurrentPlayerIdx;   // Index of the Current Player.
    private int         TotalTurns;         // Total turns completed amongst all players.
    private int         MaxPoints;          // Variable to hold maximum points for current round.
    private int         Winner;             // Index if the player which is currently winning.
    private int         Suite;              // Suite of the cards currently in play.
    private int         Hukkam;
    private boolean     IsHukkamValid;
    private int         NumMendisInThisHand;

    private ImageAdapter    ImgAdapter;

    // TODO: Add support for Arraylist for Table Cards

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_grid);

        // Set references for Table Cards.
        TableCard1 = (ImageView)findViewById(R.id.TableCard1);
        TableCard2 = (ImageView)findViewById(R.id.TableCard2);
        TableCard3 = (ImageView)findViewById(R.id.TableCard3);
        TableCard4 = (ImageView)findViewById(R.id.TableCard4);

        PlayerName = (TextView)findViewById(R.id.PlayerTextView);

        // Set references to GridView for cards in players hand.
        PlayerCardsGrid = (GridView)findViewById(R.id.PlayerCards);

        CardGame = HomeScreen.CardGame;
        ImageView   PlayerCardView;

        ImgAdapter = new ImageAdapter(this);

        PlayerCardsGrid.setAdapter(ImgAdapter);

        PlayerCardsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GamePlay.this, "" + position, Toast.LENGTH_SHORT).show();
                CardSelected = position;
                TableCard.setImageResource(CurrentPlayer.CardsInHand.get(CardSelected).GetResId());
            }
        });

        // Get Current Player name.
        PlayerName = (TextView)findViewById(R.id.PlayerTextView);

        // Initialize Class variables.
        CardSelected        = -1;   // Initially no card is selected by the player.
        CurrentPlayerIdx    = 0;    // At start first player is the one who has to play.
        TotalTurns          = 0;
        MaxPoints           = 0;
        Winner              = CurrentPlayerIdx;
        CurrentPlayer       = CardGame.getPlayer(CurrentPlayerIdx);
        IsHukkamValid       = false;    // Initially there is no Hukkam.
        TableCard           = GetTableCard(CurrentPlayerIdx);

        // Set the name of the Player in the Text view whose turn it is right now.
        PlayerName.setText(CurrentPlayer.GetName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets the Image of the Cards on Table with card_back.
     * @param table Table object to be cleared.
     */
    public void ClearTable(Table table)
    {
        int NumCardsOnTable = table.GetNoOfCardsOnTable();

        // Set the ResIds to be card_back
        TableCard1.setImageResource(R.drawable.card_back);
        TableCard2.setImageResource(R.drawable.card_back);
        TableCard3.setImageResource(R.drawable.card_back);
        TableCard4.setImageResource(R.drawable.card_back);

        // Remove all the cards from Table.
        for(int i = 0; i < NumCardsOnTable; i++)
        {
            table.RemoveCard(0);
        }

        Winner = CurrentPlayerIdx;
        MaxPoints = 0;
    }

    /**
     * Checks if the player has played card of the suite which is currently played on table.
     * Player can play card from another suit only if he does not have card of current suite.
     * The player who first plays card from another suite will set the Hukkam.
     * After Hukkam is set cards left in all players hands will be updated to have more points.
     * If already Hukkam is set and if player plays card from other suite then it is a fuse that
     * card is just invalid(Point = 0).
     *
     * @param Player        player object which is currently playing.
     * @param Suite         suite which is currently going on Table.
     * @param CurrentCard   Card which is just played by a Player.
     */
    public boolean  IsCurrentCardPlayedValid(Player Player, int Suite, Card CurrentCard)
    {
        Card PlayerCard;
        for(int i = 0; i < Player.getNoOfCardsInHand(); i++)
        {
            PlayerCard = Player.GetCard(i);
            if(Suite == PlayerCard.GetSuite())
            {
                return false;
            }
        }

        if(IsHukkamValid == false)
        {
            IsHukkamValid   = true;
            Hukkam          = CurrentCard.GetSuite();

            // Increase Points for Current Card
           // CurrentCard.SetPoints(CurrentCard.GetPoints() + CardGame.ADDITIONAL_POINTS_FOR_HUKKAM);

            // Increase points of the Hukkam Cards in all players.
            CardGame.UpdateCardPointsAfterHukkamValid(Hukkam);
        }
        // If the card is not of the same suit as the suite round and if the card is not of hukkam then that card is FUSE points = 0
        else if(CurrentCard.GetSuite() != Hukkam)
        {
            // It is a FUSE Points for Current Card is 0
            CurrentCard.SetPoints(0);
        }
        return true;
    }

    /**
     * Gets the ImageView of the Card on Table.
     *
     * @param   Turn of the Player.
     * @return  ImageView of the corresponding TableCard.
     */
    public ImageView GetTableCard(int Turn)
    {
        switch(Turn)
        {
            case 0:
                return TableCard1;

            case 1:
                return TableCard2;

            case 2:
                return TableCard3;

            case 3:
                return TableCard4;
        }
        return TableCard1;
    }

    /**
     * Set the image in the corresponding card to be card_back and remove the card from players list.
     * Add the card to Tables list. Make a decision on the hand if one hand completes. Make decision on Game when it completes.
     *
     * @param   view of the Play Button.
     */
    public void PlayBtnClick(View view)
    {
        Card    CurrentCard;
        Table   Table = CardGame.getTable();
        int     Points;
        int     CardIdx = 0;
        if(CardSelected == -1)
        {
            return;
        }

        // Get the Card which Player wants to Play from CardsInHand.
        CurrentCard = CurrentPlayer.GetCard(CardSelected);

        // Validate the card that was played
        if(TotalTurns % CardGame.MAX_CARDS_ON_TABLE == 0)
        {
            // This is the First Card after the Last Round. Suite is set by this card.
            Suite = CurrentCard.GetSuite();
        }
        else
        {
            if(Suite != CurrentCard.GetSuite())
            {
                if(IsCurrentCardPlayedValid(CurrentPlayer, Suite, CurrentCard) == false)
                {
                    //Raise a Toast to warn Player not to cheat
                    Toast toast = Toast.makeText(this, "Play the card which is currently running on the Table",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        }

        // Update the No Of Mendis in this Hand.
        CardIdx = CurrentCard.GetIndex();
        if(CardIdx == 16 || CardIdx == 17 || CardIdx == 18 || CardIdx == 19 )
        {
            NumMendisInThisHand++;
        }

        TotalTurns++;

        Points = CurrentCard.GetPoints();

        // If points from this card is Maximum then mark this player as winner.
        if(Points > MaxPoints)
        {
            MaxPoints = Points;
            Winner    = CurrentPlayerIdx;
        }

        // Set the ImageView of this card to the card_back so that player does not click it.
        // Also se the Index to be CARDS_IN_A_DECK which is one after max Number of Cards.
        CurrentPlayer.GetCard(CardSelected).SetResId(R.drawable.card_back);
        CurrentPlayer.GetCard(CardSelected).SetIndex(CardGame.CARDS_IN_A_DECK);

        // Remove this card from Players CardsInHand.
        CurrentPlayer.RemoveCard(CardSelected);

        // Add this card to Cards on Table.
        Table.AddCard(CurrentCard);

        // If Total Turns is divisible by Max No Of Cards On Table then one Round is complete.
        // Reset Table with card_back.
        if(TotalTurns % CardGame.MAX_CARDS_ON_TABLE == 0)
        {
            CurrentPlayerIdx = Winner;

            // Add cards from table onto winner Player's CardsFromHand.
            CardGame.getPlayer(Winner).AddCardsFromHands(Table);

            // All cards in the Table are done, Clear the Table.
            ClearTable(Table);

            // Update the Team with this Hand.
            CardGame.GetTeam(Winner).AddAHand();

            // Update the Team with Mendis in This Hand
            CardGame.GetTeam(Winner).AddAMendis(NumMendisInThisHand);

            // Update CardsFromHands in the player who has won the hand.
            MaxPoints = 0;
            NumMendisInThisHand = 0;
        }
        else
        {
            // Get the next player who has to play. Table is not full so just get the next player.
            CurrentPlayerIdx = (CurrentPlayerIdx + 1)%CardGame.GetNumPlayers();
        }

        ImgAdapter.SetPlayerIdx(CurrentPlayerIdx);

        ImgAdapter.notifyDataSetChanged();
        PlayerCardsGrid.setAdapter(ImgAdapter);
        CurrentPlayer = CardGame.getPlayer(CurrentPlayerIdx);
        //InitializeCardsInLayout(CurrentPlayer);
        TableCard = GetTableCard(CurrentPlayerIdx);

        // Reset the CardSelected to -1
        CardSelected = -1;

        // One entire Game is over.
        if(TotalTurns == CardGame.CARDS_IN_A_DECK)
        {
            Team WinnerTeam = CardGame.MakeGameDecision();

            // TODO: Raise a Toast for Winner Team.
            IsHukkamValid = false;

            // Start GameDone Activity to offer users to choose.
            Intent intent = new Intent(this, GameDone.class);
            startActivity(intent);
        }
    }
}
