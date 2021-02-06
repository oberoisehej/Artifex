package com.example.sehejoberoi.finalia;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sehejoberoi on 8/2/18.
 */

public class sendStartDrawRequest extends StringRequest {
    private static final String Request_Join_URL="https://socsia.000webhostapp.com/sendStartDraw.php";

    private Map<String, String> params;

    public sendStartDrawRequest(String pin, String turn, Response.Listener<String> listener){
        super(Request.Method.POST, Request_Join_URL, listener, null);
        params=new HashMap<>();
        params.put("pin",pin);
        params.put("turn",turn);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
