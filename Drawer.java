package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class Drawer extends AppCompatActivity {

    private CanvasView canvasView;
    private TextView word;
    private TextView guesses;
    private TextView timer;

    private String indexg="0";

    private String re="a";
    private String all="";
    private boolean change=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        canvasView = (CanvasView)findViewById(R.id.canvas);
        canvasView.setDrawingCacheEnabled(true);

        word = (TextView) findViewById(R.id.word);
        guesses = (TextView) findViewById(R.id.guesses);
        timer = (TextView) findViewById(R.id.timer);

        word.setText(Board.getNextWord());
        timer();

    }
    public void timer(){
        CountDownTimer countDownTimer = new CountDownTimer(90 * 1000, 1000) {
            RequestQueue queue = Volley.newRequestQueue(Drawer.this);
            RequestQueue queue1 = Volley.newRequestQueue(Drawer.this);
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((int) millisUntilFinished / 1000));
                    for (int i = 0; i < CanvasView.xs.size(); i++) {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        };
                        sendDrawingRequest send = new sendDrawingRequest(GamePinEnter.getGamePin() + Board.turnNo+"d", CanvasView.moves.get(i), CanvasView.xs.get(i), CanvasView.ys.get(i), CanvasView.colourSend.get(i), CanvasView.thicknessSend.get(i), responseListener);
                        queue.add(send);
                    }
                    CanvasView.moves.clear();
                    CanvasView.xs.clear();
                    CanvasView.ys.clear();
                    CanvasView.thicknessSend.clear();
                    CanvasView.colourSend.clear();

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
                        System.out.println(parts.length + " " + parts[0]);

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
                        if (split.length > 8) {
                            String guessesList = "";
                            for (int i = 0; i < 8; i++) {
                                guessesList = guessesList + split[split.length - 7 + i] + "\n";
                            }
                            guesses.setText(guessesList);
                        }
                        change = false;
                    }
                }
                }


            @Override
            public void onFinish() {
                canvasView.cleanCanvas();
                Drawer.this.finish();
                if (Board.getTeamPlaying()){
                    Board.setTeamARoll(false);
                }else{
                    Board.setTeamBRoll(false);
                }
            }
        };
        countDownTimer.start();
    }
    public void prop(View v){
        Intent property = new Intent(this, properties.class);
        startActivity (property);
    }
    public void clear(View v){
        canvasView.cleanCanvas();
    }
    public void undo(View v){
        if (canvasView.paths.size()>0) {
            canvasView.undo();
        }
    }
    public void redo(View v){
        if (canvasView.rPaths.size()>0) {
            canvasView.redo();
        }
    }
    public void eraser(View v){
        properties.eraserColour=properties.colour;
        properties.colour= Color.argb(0xFF, 0xFA, 0xFA, 0xFA);
        properties.eraserThickness =properties.thick;
        properties.thick=50;
        properties.eraser=true;
        properties.change=true;
    }
    public void pen(View v){
        if (properties.eraser) {
            properties.colour = properties.eraserColour;
            properties.thick=properties.eraserThickness;
        }
        properties.eraser=false;
        properties.change=true;
    }
    public void done(){
        CountDownTimer countDownTimer = new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                canvasView.cleanCanvas();
                finish();
                if (Board.getTeamPlaying()){
                    Board.setTeamARoll(true);
                }else{
                    Board.setTeamBRoll(true);
                }
                Drawer.this.finish();
            }
        };
        countDownTimer.start();
    }
}
