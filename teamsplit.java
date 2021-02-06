package com.example.sehejoberoi.finalia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class teamsplit extends AppCompatActivity {
    private static boolean onTeamA=true;
    private String list="a";
    private static String[] broken;
    private int count=0;

    public TextView teamA;
    public TextView teamB;

    public static String ta="";
    public static String tb="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamsplit);

        teamA = (TextView) findViewById(R.id.teamA);
        teamB = (TextView) findViewById(R.id.teamB);
        teamA.setText("");
        teamB.setText("");
    }
    @Override
    protected void onStart(){
        super.onStart();
            CountDownTimer countDownTimer = new CountDownTimer(12 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                list = response;
                            }
                        };
                        getTeams join = new getTeams(GamePinEnter.getGamePin(), responseListener);
                        RequestQueue queue = Volley.newRequestQueue(teamsplit.this);
                        queue.add(join);
                    broken = list.split("\n");
                    if (count < broken.length) {
                        StringBuilder sb = new StringBuilder(broken[count]);
                        if (broken[count].charAt(broken[count].length() - 1) == '1') {
                            String name = sb.deleteCharAt(broken[count].length() - 1).toString() + "\n";
                            if(name.equals(PrivateGameName.getName()+"\n")){
                                onTeamA=true;
                            }
                            teamA.setText(teamA.getText().toString() + name);
                            count++;
                        } else if (broken[count].charAt(broken[count].length() - 1) == '0') {
                            String name = sb.deleteCharAt(broken[count].length() - 1).toString() + "\n";
                            if(name.equals(PrivateGameName.getName()+"\n")){
                                onTeamA=false;
                            }
                            teamB.setText(teamB.getText().toString() + name);
                            count++;
                        }

                    }
                }

                @Override
                public void onFinish() {
                    Intent start = new Intent(teamsplit.this, Board.class);
                    startActivity(start);
                    if(PrivateGameChoose.getIsHost()){
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        };
                        setUpGameRequest join = new setUpGameRequest(GamePinEnter.getGamePin()+"b", responseListener);
                        RequestQueue queue = Volley.newRequestQueue(teamsplit.this);
                        queue.add(join);
                        ta=teamA.getText().toString();
                        tb=teamB.getText().toString();
                    }
                    teamsplit.this.finish();
                }
            };
            countDownTimer.start();
    }
    public static boolean getOnTeamA(){
        return onTeamA;
    }
}
