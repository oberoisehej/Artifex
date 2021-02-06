package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class GamePinEnter extends AppCompatActivity {
    private EditText pin;
    private static String gamePin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pin_enter);
        pin = (EditText) findViewById(R.id.gamePin);
    }
    public void enterGame(View v){
        gamePin=pin.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                String success = response;
                if (success.equals("1")) {
                    Intent enter;
                    enter = new Intent (GamePinEnter.this, PrivateGameName.class);
                    startActivity(enter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GamePinEnter.this);
                    builder.setMessage("No game with this pin exists")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        };
        privateGameJoinRequest join = new privateGameJoinRequest(gamePin, responseListener);
        RequestQueue queue = Volley.newRequestQueue(GamePinEnter.this);
        queue.add(join);

    }
    public static String getGamePin(){
        return gamePin;
    }
    public static void setGamePin(String pin){
        gamePin=pin;
    }

}
