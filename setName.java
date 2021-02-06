package com.example.sehejoberoi.finalia;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sehejoberoi on 28/11/17.
 */

public class setName extends StringRequest{
    private static final String Request_Join_URL="https://socsia.000webhostapp.com/privateGameName.php";

    private Map<String, String> params;

    public setName(String Username, Response.Listener<String> listener){
        super(Request.Method.POST, Request_Join_URL, listener, null);
        params=new HashMap<>();
        params.put("username",Username);
        params.put("table", GamePinEnter.getGamePin());

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
