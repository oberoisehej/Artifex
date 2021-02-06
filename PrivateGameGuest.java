package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class PrivateGameGuest extends AppCompatActivity {
    public TextView list;
    private String success="";
    private boolean done=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_game_guest);

        list = (TextView) findViewById(R.id.playerList);


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String success = response;
                list.setText(success);
            }
        };
        showNameRequest join = new showNameRequest(GamePinEnter.getGamePin(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(PrivateGameGuest.this);
        queue.add(join);


    }
    protected void onStart(){
        super.onStart();
        CountDownTimer countDownTimer = new CountDownTimer(300 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.equals(success) && (response.charAt(0)!='0' && response.charAt(0)!='1') ) {
                            success = response;
                            list.setText(success);
                        }
                        if (response.charAt(0)=='1'){
                            done=true;
                        }
                    }
                };
                showNameRequest join = new showNameRequest(GamePinEnter.getGamePin(), responseListener);
                getStartRequest start = new getStartRequest(GamePinEnter.getGamePin(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(PrivateGameGuest.this);
                queue.add(join);
                queue.add(start);
                if (done) {
                    this.cancel();
                    Intent startGame = new Intent(PrivateGameGuest.this, teamsplit.class);
                    startActivity(startGame);
                    PrivateGameGuest.this.finish();
                }
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }
}
