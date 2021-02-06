package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class PrivateGameHost extends AppCompatActivity {
    private TextView list;
    private String success="";
    private static int length=1;
    private boolean done=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_game_host);
        list = (TextView) findViewById(R.id.textView5);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                success = response;
                list.setText(success);

            }
        };
        showNameRequest join = new showNameRequest(GamePinEnter.getGamePin(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(PrivateGameHost.this);
        queue.add(join);
    }
    public void startGame(View v){
        length=success.split("\n").length;
        if (length>=4 && length<=8) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int length=PrivateGameHost.getLength() / 2;
                    if (PrivateGameHost.getLength()%2!=0){
                        length++;
                    }
                    int[] nums = new int[length];
                    Random rand = new Random();

                    for (int i = 0; i < nums.length; i++) {
                        boolean used = false;
                        int number = rand.nextInt(PrivateGameHost.getLength() - 1) + 1;

                        for (int a = 0; a < i; a++) {
                            if (number == nums[a]) {
                                used = true;
                            }
                        }
                        if (!used) {
                            nums[i] = number;
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                }
                            };
                            teamSplitRequest join = new teamSplitRequest(GamePinEnter.getGamePin(), number, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(PrivateGameHost.this);
                            queue.add(join);

                        } else {
                            i--;
                        }

                    }
                    Intent start = new Intent(PrivateGameHost.this, teamsplit.class);
                    startActivity(start);
                    done=true;
                    PrivateGameHost.this.finish();

                }
            };
            startGame join = new startGame(GamePinEnter.getGamePin(), length, responseListener);
            RequestQueue queue = Volley.newRequestQueue(PrivateGameHost.this);
            queue.add(join);

        }else if(length<4){
            AlertDialog.Builder builder = new AlertDialog.Builder(PrivateGameHost.this);
            builder.setMessage("Not Enough People in the Game (Minimun 4 people)")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(PrivateGameHost.this);
            builder.setMessage("Too many People in the Game (Maximum 8 people)")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
    }
    protected void onStart(){
        super.onStart();
        CountDownTimer countDownTimer = new CountDownTimer(300 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.equals(success)) {
                            success = response;
                            list.setText(success);
                            //length=length+1;
                        }
                    }
                };
                showNameRequest join = new showNameRequest(GamePinEnter.getGamePin(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(PrivateGameHost.this);
                queue.add(join);
                if (done){
                    this.cancel();
                }

            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }
    public static int getLength(){
        return length;
    }
    public static void setLength(int len){
        length=len;
    }
}
