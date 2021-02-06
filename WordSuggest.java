package com.example.sehejoberoi.finalia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class WordSuggest extends AppCompatActivity {

    private EditText guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_suggest);

        guess = (EditText) findViewById(R.id.editText);

    }
    public void enter(View v){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Toast done = Toast.makeText(getApplicationContext(),
                        "Thank you! The suggestion has been made", Toast.LENGTH_SHORT);
                done.show();
                WordSuggest.this.finish();
            }
        };
        wordSuggestRequest join = new wordSuggestRequest(guess.getText().toString(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(WordSuggest.this);
        queue.add(join);
    }

}
