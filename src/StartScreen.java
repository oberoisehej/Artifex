package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }
    public void PrivateGame(View v) {
        Intent pGame = new Intent(this, PrivateGameChoose.class);
        startActivity(pGame);
        finish();
    }
    public void RandomGame(View v){
        Intent rGame = new Intent(this, RandomGameName.class);
        startActivity(rGame);
        finish();
    }
    public void newWord(View v){
        Intent word = new Intent(this, WordSuggest.class);
        startActivity(word);

    }

}
