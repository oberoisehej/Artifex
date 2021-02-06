package com.example.sehejoberoi.finalia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RandomGameWait extends AppCompatActivity {
    private TextView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randome_game_wait);
        list = (TextView) findViewById(R.id.players);
        list.setText("bob");
        list.setText(list.getText().toString()+"\n"+RandomGameName.getName());
    }
}
