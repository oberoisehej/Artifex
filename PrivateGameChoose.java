package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PrivateGameChoose extends AppCompatActivity {
    private static boolean host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_game_choose);
    }
    public void createGame(View v){
        host = true;
        Intent create = new Intent(this, GamePinCreate.class);
        startActivity(create);

    }
    public void joinGame(View v){
        host = false;
        Intent join = new Intent(this, GamePinEnter.class);
        startActivity(join);

    }
    public static boolean getIsHost(){
        return host;
    }
}
