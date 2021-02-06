package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class GamePinCreate extends AppCompatActivity {
    private TextView pin;
    private int gamePin=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random rand = new Random();
        gamePin =  rand.nextInt(9999) + 1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pin_create);
        pin=(TextView)findViewById(R.id.GamePin);
        pin.setText("A"+Integer.toString(gamePin));
    }
    public void enterGame(View v){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String success = response;
                System.out.println(response);
                if (success.equals("1")) {
                    GamePinEnter.setGamePin("A"+Integer.toString(gamePin));
                    Intent enter;
                    enter = new Intent (GamePinCreate.this, PrivateGameName.class);
                    startActivity(enter);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(GamePinCreate.this);
                    builder.setMessage("Game Pin Taken...Try again")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    Random rand = new Random();
                    gamePin =  rand.nextInt(9999) + 1;
                    pin.setText("A"+Integer.toString(gamePin));
                }
            }
        };
        privateGameCreateRequest join = new privateGameCreateRequest("A"+Integer.toString(gamePin), responseListener);
        RequestQueue queue = Volley.newRequestQueue(GamePinCreate.this);
        queue.add(join);
    }
}
