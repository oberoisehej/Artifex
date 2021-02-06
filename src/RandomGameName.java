package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RandomGameName extends AppCompatActivity {
    private EditText inName;
    private static String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game_name);

        inName = (EditText)findViewById(R.id.Name);
    }
    public void enterGame(View v){
        name = inName.getText().toString();
        Intent enter = new Intent (this, RandomGameWait.class);
        startActivity (enter);
    }
    public static String getName(){
        return name;
    }
}
