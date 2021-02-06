package com.example.sehejoberoi.finalia;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by sehejoberoi on 11/2/18.
 */

public class newWordRequest extends StringRequest {
    private static final String Request_Join_URL="https://socsia.000webhostapp.com/newWord.php";

    private Map<String, String> params;

    public newWordRequest(String pin, String turn, Response.Listener<String> listener){
        super(Request.Method.POST, Request_Join_URL, listener, null);

        Random rand = new Random();
        int num = rand.nextInt(24);

        params=new HashMap<>();
        params.put("pin",pin);
        params.put("turn",turn);
        params.put("num", num+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
