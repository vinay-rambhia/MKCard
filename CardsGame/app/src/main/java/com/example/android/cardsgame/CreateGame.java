package com.example.android.cardsgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class CreateGame extends Activity {

    private Player  player;
    private Game    CardGame;
    private ArrayList<EditText> PlayerNames = new ArrayList<EditText>();
    private EditText    PlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);

        CardGame = HomeScreen.CardGame;

        InitializeEditText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_play, menu);
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

    public void InitializeEditText()
    {
        // TODO: Edit Text is annoying to delete player name and add another name.
        PlayerName = (EditText) findViewById(R.id.AddPlayer1);
        PlayerName.setText("Player1", EditText.BufferType.EDITABLE);
        PlayerNames.add(PlayerName);

        PlayerName = (EditText) findViewById(R.id.AddPlayer2);
        PlayerName.setText("Player2", EditText.BufferType.EDITABLE);
        PlayerNames.add(PlayerName);

        PlayerName = (EditText) findViewById(R.id.AddPlayer3);
        PlayerName.setText("Player3", EditText.BufferType.EDITABLE);
        PlayerNames.add(PlayerName);

        PlayerName = (EditText) findViewById(R.id.AddPlayer4);
        PlayerName.setText("Player4", EditText.BufferType.EDITABLE);
        PlayerNames.add(PlayerName);
    }

    public void AddPlayersClickListener(View view)
    {
        for(int i = 0; i < CardGame.GetNumPlayers(); i++)
        {
            player = CardGame.getPlayer(i);
            player.SetName(PlayerNames.get(i).getText().toString());
        }

        // Start the Activity to Start the Game.
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }
}
