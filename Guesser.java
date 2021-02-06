package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class Guesser extends AppCompatActivity {

    private TextView word;
    private TextView guesses;
    private TextView timer;

    private CanvasView canvasView;

    private EditText guess;

    private static boolean finished=false;

    private String indexd="0";
    private String indexg="0";

    private String re="a";
    private String all="";
    private boolean change=false;

    private static List<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guesser);
        word = (TextView) findViewById(R.id.word);
        guesses = (TextView) findViewById(R.id.guesses);
        guess = (EditText) findViewById(R.id.EnteredGuess);
        canvasView=(CanvasView) findViewById(R.id.canvasView);
        canvasView.setDrawingCacheEnabled(true);

        timer = (TextView) findViewById(R.id.timer);

        int len=Board.getNextWord().length();
        String newWord="";
        for (int i=0;i<len;i++){
            newWord=newWord+"_ ";
        }
        word.setText(newWord);

    }
    @Override
    protected void onStart(){
        super.onStart();
        timer();

    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }

    public void timer(){
         CountDownTimer countDownTimer = new CountDownTimer(90 * 1000, 1000) {
             RequestQueue queue = Volley.newRequestQueue(Guesser.this);
             RequestQueue queue1= Volley.newRequestQueue(Guesser.this);
            @Override
            public void onTick(long millisUntilFinished) {
                canvasView.invalidate();
                if (!finished) {
                    timer.setText(String.valueOf((int) millisUntilFinished / 1000));
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            data = Arrays.asList(response.split("\n"));
                        }
                    };
                    getDrawingRequest join = new getDrawingRequest(GamePinEnter.getGamePin()+Board.turnNo+"d", indexd, responseListener);
                    queue.add(join);
                    for (int i = 0; i < data.size() && data.size() > 1; i++) {
                        String[] record = data.get(i).split(",");
                        indexd = record[0];
                        properties.colour = Integer.valueOf(record[4]);
                        properties.thick = Integer.valueOf(record[5]);
                        switch (record[1]) {
                            case "0": //action int value 0
                                canvasView.startTouch(Float.valueOf(record[2]), Float.valueOf(record[3]));
                                break;
                            case "2": //action int value 2
                                canvasView.moveTouch(Float.valueOf(record[2]), Float.valueOf(record[3]));
                                break;
                            case "1": //action int value 1
                                canvasView.upTouch();
                                break;
                            default:
                                System.out.println("didn't work");
                        }

                    }
                    //canvasView.invalidate();
                    if (millisUntilFinished/1000%2 == 0){
                        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (!re.equals(response)) {
                                    re = response;
                                    change=true;
                                }else{
                                    change=false;
                                }
                            }
                        };
                        getGuessRequest get = new getGuessRequest(GamePinEnter.getGamePin()+Board.turnNo+"g", indexg, responseListener1);
                        queue1.add(get);
                        if (change) {
                            String[] parts = re.split("\n");
                            String[] records = parts[0].split(",");

                            for (int i = 0; i < parts.length && records.length > 2; i++) {
                                records = parts[i].split(",");
                                indexg = records[0];
                                if (records[2].equals(Board.getNextWord())) {
                                    all = "Word: " + records[2] + " Found by: " + records[1];
                                    done();
                                    break;
                                }
                                all = all + records[1] + ": " + records[2] + "\n";

                            }
                            guesses.setText(all);


                            String[] split = all.split("\r?\n");
                            if (split.length > 7) {
                                String guessesList = "";
                                for (int i = 0; i < 7; i++) {
                                    guessesList = guessesList + split[split.length - 7 + i] + "\n";
                                }
                                guesses.setText(guessesList);
                            }
                            change = false;
                        }
                    }
                }

            }

            @Override
            public void onFinish() {
                canvasView.cleanCanvas();
                if (Board.getTeamPlaying()){
                    Board.setTeamARoll(false);
                }else{
                    Board.setTeamBRoll(false);
                }
                Guesser.this.finish();
            }
        };
        countDownTimer.start();
    }
    public void enter(View v){
        String newGuess=guess.getText().toString();
        guess.setText("");
        if (newGuess.equals(" ")|| newGuess.equals("") || newGuess.contains(",")|| newGuess.contains("\n")){
            guess.setText("invalid guess");
        }else {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            };
            sendGuessRequest join = new sendGuessRequest(GamePinEnter.getGamePin()+Board.turnNo+"g", newGuess, PrivateGameName.getName(), responseListener);
            RequestQueue queue = Volley.newRequestQueue(Guesser.this);
            queue.add(join);
        }

    }
    public void done(){
        CountDownTimer countDownTimer = new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                finished=true;
                canvasView.cleanCanvas();
                finish();
                if (Board.getTeamPlaying()){
                    Board.setTeamARoll(true);
                }else{
                    Board.setTeamBRoll(true);
                }
                Guesser.this.finish();
            }
        };
        countDownTimer.start();
    }
    public static boolean isFinished(){
        return finished;
    }
}

