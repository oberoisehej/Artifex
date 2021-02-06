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

public class wordSuggestRequest extends StringRequest {
    private static final String Request_Join_URL="https://socsia.000webhostapp.com/wordSuggest.php";

    private Map<String, String> params;

    public wordSuggestRequest(String word, Response.Listener<String> listener){
        super(Request.Method.POST, Request_Join_URL, listener, null);

        params=new HashMap<>();
        params.put("word",word);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
