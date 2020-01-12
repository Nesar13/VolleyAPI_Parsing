package com.example.volleyapi_parsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private RequestQueue requestQueue;


    //https://jsonplaceholder.typicode.com/todos/1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this); //intializing the requestQueue


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.d("JSON: ", "onResponse: " + response.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR: ", "onErrorResponse: " + error.getMessage());

            }
        });

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Log.d("Titles: ", "onResponse: "+jsonObject.getString("title")+" "+jsonObject.getBoolean("completed"));
                        Boolean b=jsonObject.getBoolean("completed");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/weather?q=London,uk", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

//
//
//                try {
                    Log.d("Weather ", "onResponse: "+response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

//                        Boolean b=jsonObject.getBoolean("completed");

                    }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest1); //adding to queue
        requestQueue.add(jsonArrayRequest);
        requestQueue.add(jsonObjectRequest);


    }
}
