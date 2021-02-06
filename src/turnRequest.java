package com.example.sehejoberoi.finalia;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sehejoberoi on 8/2/18.
 */

public class turnRequest extends StringRequest {
    private static final String Request_Join_URL="https://socsia.000webhostapp.com/turn.php";

    private Map<String, String> params;

    public turnRequest(String pin, String drawer, Response.Listener<String> listener){
        super(Request.Method.POST, Request_Join_URL, listener, null);
        params=new HashMap<>();
        params.put("pin",pin);
        params.put("drawer", drawer);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
