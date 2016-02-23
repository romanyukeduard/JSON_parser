package io.github.tripguider.jsonp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.tripguider.jsonp.app.AppController;

public class MainActivity extends AppCompatActivity {

    private String urlJsonArray = "http://tripguider.github.io/categories.json";
    private String urlJsonObj = "http://romanyukeduard.github.io/my.json";

    private static String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private Button obj, arr;

    private TextView txtResponse;
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txtResponse = (TextView) findViewById(R.id.txt);

        obj = (Button) findViewById(R.id.obj);
        arr = (Button) findViewById(R.id.arr);

        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeJsonObjReq(urlJsonObj);

            }
        });

        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeJsonRequest(urlJsonArray);

            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }

    private void makeJsonObjReq(String url) {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, (String)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    jsonResponse="";
                    JSONArray data = response.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {

                        JSONObject name = (JSONObject) data
                                .get(i);

                        String title = name.getString("name");


                        jsonResponse += "Name: " + title + "\n\n";

                    }

                    txtResponse.setText("");
                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    private void makeJsonRequest(String url) {

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String name = person.getString("name");

                                jsonResponse += "Name: " + name + "\n\n";

                            }

                            txtResponse.setText("");
                            txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
