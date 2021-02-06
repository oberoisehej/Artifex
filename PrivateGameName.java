package com.example.sehejoberoi.finalia;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PrivateGameName extends AppCompatActivity {
    private static String name;
    private EditText uName;
    private boolean unique=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_game_name);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        uName=(EditText) findViewById(R.id.Name);
    }

    public void enterGame(View v){

        name = uName.getText().toString();

        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String [] n=response.split("\n");
                for (int i=0;i<n.length;i++) {
                    if (n[i].equals(name)){
                        unique=false;
                        //break;
                    }
                }
                if (unique) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String success = response;
                            if (success.equals("1")) {
                                Intent enter;
                                if (PrivateGameChoose.getIsHost()) {
                                    enter = new Intent(PrivateGameName.this, PrivateGameHost.class);
                                } else {
                                    enter = new Intent(PrivateGameName.this, PrivateGameGuest.class);
                                }
                                startActivity(enter);
                                PrivateGameName.this.finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PrivateGameName.this);
                                builder.setMessage("Login Failed. Try again")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }
                    };
                    setName join = new setName(name, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PrivateGameName.this);
                    queue.add(join);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(PrivateGameName.this);
                    builder.setMessage("Username is taken!")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    unique=true;
                }
            }
        };
        showNameRequest names = new showNameRequest(GamePinEnter.getGamePin(), response);
        RequestQueue queue1 = Volley.newRequestQueue(PrivateGameName.this);
        queue1.add(names);



    }
    public static String getName(){
        return name;
    }
}
