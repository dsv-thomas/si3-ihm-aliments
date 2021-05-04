package com.dsv.td1.si3_ihm_aliments.helpers;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

import java.io.File;
import java.util.Date;

public class LocalisationFinder extends AppCompatActivity {

    private JSONArray jsonArray;

    public LocalisationFinder() {
    }

    public void findLocation(File file, Producer producer, String place, Date date, Date time, Date time2) throws JSONException {
        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(file, 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();

        String url ="https://api-adresse.data.gouv.fr/search/?q="+place;
        final JSONObject[] jsonObject = new JSONObject[1];

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        try {
                            JSONObject object = new JSONObject(response);
                            jsonObject[0] = (JSONObject) object.getJSONArray("features").get(0);
                            Log.d("APICALL", String.valueOf(jsonObject[0].getJSONObject("geometry").getJSONArray("coordinates")));
                            jsonArray = jsonObject[0].getJSONObject("geometry").getJSONArray("coordinates");
                            Model_Producer.getInstance().addPickupPoint(producer, new PickupPoint(producer, place, date, time, time2, new GeoPoint(Double.parseDouble(jsonArray.get(1).toString()), Double.parseDouble(jsonArray.get(0).toString()))));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });


        requestQueue.add(stringRequest);
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }
}
