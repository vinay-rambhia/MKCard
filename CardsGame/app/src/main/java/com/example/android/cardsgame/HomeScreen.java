package com.example.android.cardsgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeScreen extends ActionBarActivity {

    public static Game CardGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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

    public void CreateGameBtnListner (View view)
    {
        CardGame = new Game();
        CardGame.InitializeResIds();
        CardGame.AddPlayers();
        CardGame.Shuffle();

        // Creating an Intent to start CreateGame Activity
        Intent intent = new Intent(this,CreateGame.class);
        startActivity(intent);
    }

    public void JoinGameBtnListner(View view)
    {

    }
}
