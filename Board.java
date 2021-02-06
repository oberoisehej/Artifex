package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class Board extends AppCompatActivity {

    private TextView diceRoll;
    private TextView turn;

    private static boolean drawing=false;
    private String drawer=" ";

    private static boolean teamARoll=true;
    private static boolean teamBRoll=true;
    private static boolean teamATurn=false;

    public static int turnNo=0;

    private static String nextWord=" ";

    private String[] ta = teamsplit.ta.split("\n");
    private int taturn=0;
    private String[] tb = teamsplit.tb.split("\n");
    private int tbturn=0;

    private int rand=-1;

    private Intent play;

    private int taScore=0;
    private int tbScore=0;

    private TextView s0;
    private TextView s1;
    private TextView s2;
    private TextView s3;
    private TextView s4;
    private TextView s5;
    private TextView s6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        diceRoll = (TextView) findViewById(R.id.diceNumber);
        turn = (TextView) findViewById(R.id.Turn);

        /*s0 = (TextView) findViewById(R.id.textView12);
        s1 = (TextView) findViewById(R.id.s1);
        s2 = (TextView) findViewById(R.id.s2);
        s3 = (TextView) findViewById(R.id.s3);
        s4 = (TextView) findViewById(R.id.s4);
        s5 = (TextView) findViewById(R.id.s5);
        s6 = (TextView) findViewById(R.id.s6);*/
    }
    @Override
    protected void onResume(){
        super.onResume();
        diceRoll.setText("");
        teamATurn=!teamATurn;
        turnNo++;
        play=null;

        if (PrivateGameChoose.getIsHost()){
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            };
            String draw;
            if (teamATurn){
                draw=ta[taturn%ta.length];
                taturn++;
            }else{
                draw=tb[tbturn%tb.length];
                tbturn++;
            }
            turnRequest join = new turnRequest(GamePinEnter.getGamePin()+"b", draw, responseListener);
            RequestQueue queue = Volley.newRequestQueue(Board.this);
            queue.add(join);

            Response.Listener<String> response = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            };
            newWordRequest n = new newWordRequest(GamePinEnter.getGamePin()+"b", turnNo+"", response);
            RequestQueue queue1 = Volley.newRequestQueue(Board.this);
            queue1.add(n);

        }

        final CountDownTimer countDownTimer = new CountDownTimer(300 * 1000, 1000) {
            RequestQueue queue = Volley.newRequestQueue(Board.this);
            RequestQueue queue1 = Volley.newRequestQueue(Board.this);
            RequestQueue queue2 = Volley.newRequestQueue(Board.this);
            private boolean done = false;
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished/1000 < 296) {
                    if (drawer.equals(" ")) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (!response.equals("")) {
                                    drawer = response;
                                    turn.setText(drawer);
                                    if (drawer.equals(PrivateGameName.getName())) {
                                        drawing = true;
                                    } else {
                                        drawing = false;
                                    }
                                }
                            }
                        };
                        getDrawer join = new getDrawer(GamePinEnter.getGamePin() + "b", turnNo + "", responseListener);
                        queue.add(join);
                    }
                    if (diceRoll.getText().toString().equals("")) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (!response.split(",")[0].equals("-1")) {
                                    diceRoll.setText(response.split(",")[0]);
                                    if (response.split(",").length==1){
                                        nextWord="car";
                                    }else {
                                        nextWord = response.split(",")[1];
                                    }
                                    if (teamATurn) {
                                        taScore += Integer.valueOf(response.split(",")[0]);
                                    /*s0.setText("B");
                                    s0.setBackgroundColor(Integer.valueOf(R.id.purple));
                                    switch(taScore){
                                        case 1: s1.setText("A");
                                            s1.setBackgroundColor(Integer.valueOf(R.id.blueDark));
                                            break;
                                        case 2: s2.setText("A");
                                            s2.setBackgroundColor(Integer.valueOf(R.id.blueDark));
                                            break;
                                        case 3: s3.setText("A");
                                            s3.setBackgroundColor(Integer.valueOf(R.id.blueDark));
                                            break;
                                        case 4: s4.setText("A");
                                            s4.setBackgroundColor(Integer.valueOf(R.id.blueDark));
                                            break;
                                        case 5: s5.setText("A");
                                            s5.setBackgroundColor(Integer.valueOf(R.id.blueDark));
                                            break;
                                        case 6: s6.setText("A");
                                            s6.setBackgroundColor(Integer.valueOf(R.id.blueDark));
                                            break;
                                    }*/
                                    } else {
                                        tbScore += Integer.valueOf(response.split(",")[0]);
                                        s0.setText("");
                                    }
                                }
                            }
                        };
                        getDiceRequest get = new getDiceRequest(GamePinEnter.getGamePin() + "b", turnNo + "", responseListener);
                        queue1.add(get);
                    }
                    if (play == null) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    if (drawing) {
                                        play = new Intent(Board.this, Drawer.class);
                                    } else if (teamATurn == teamsplit.getOnTeamA()) {
                                        play = new Intent(Board.this, Guesser.class);
                                    } else {
                                        play = new Intent(Board.this, Spectator.class);
                                    }
                                    startActivity(play);
                                    done = true;
                                }
                            }
                        };
                        getStartDrawRequest join = new getStartDrawRequest(GamePinEnter.getGamePin() + "b", turnNo + "", responseListener);
                        queue2.add(join);
                    }

                    if (done) {
                    //    drawer.equals(" ");
                        this.cancel();
                    }

                }
            }


            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();

    }
    public void roll(View v) {
        Random randomNumberGenerator = new Random();
        if (drawing && rand<0){
            if ((turnNo%2==1 && teamARoll) || (turnNo%2==0 && teamBRoll)) {
                rand = randomNumberGenerator.nextInt(6)+1;
            }else{
                rand=0;
            }
            //diceRoll.setText(String.valueOf(rand + 1));
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            };
            sendDiceRequest join = new sendDiceRequest(GamePinEnter.getGamePin() + "b", turnNo+"", rand+ "", responseListener);
            RequestQueue queue = Volley.newRequestQueue(Board.this);
            queue.add(join);
        }
    }
    public void draw(View V){
        if(drawing) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            };
            sendStartDrawRequest join = new sendStartDrawRequest(GamePinEnter.getGamePin() + "b", turnNo+"", responseListener);
            RequestQueue queue = Volley.newRequestQueue(Board.this);
            queue.add(join);
        }
    }
    public static boolean getTeamPlaying (){
        return teamATurn;
    }
    public static boolean getDrawing(){
        return drawing;
    }
    public static String getNextWord(){
        return nextWord;
    }
    public static void setTeamARoll(boolean can){teamARoll=can; }
    public static void setTeamBRoll(boolean can){teamBRoll=can; }

}
