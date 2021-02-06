package com.example.sehejoberoi.finalia;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sehejoberoi on 7/2/18.
 */

public class sendDrawingRequest extends StringRequest {
    private static final String Request_Join_URL="https://socsia.000webhostapp.com/sendDrawing.php";

    private Map<String, String> params;

    public sendDrawingRequest(String pin, int type, double x, double y, int colour, int thickness, Response.Listener<String> listener){
        super(Request.Method.POST, Request_Join_URL, listener, null);
        params=new HashMap<>();
        params.put("pin",pin);
        params.put("type", type+"");
        params.put("x",x+"");
        params.put("y",y+"");
        params.put("colour",colour+"");
        params.put("thickness",thickness+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
