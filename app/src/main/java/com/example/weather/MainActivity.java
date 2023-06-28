package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Double latitude, longitude = null;
    ArrayList<String> temperatures = new ArrayList<>();
    ArrayList<String> prinTemperatures = new ArrayList<>();
    ArrayList<String> placeholder = new ArrayList<>();
    Button charabutton, confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewLocation = findViewById(R.id.location);
        TextView place = findViewById(R.id.name);
        TextView desc = findViewById(R.id.weatherDesc);
        ListView listView = findViewById(R.id.temperatures);
        EditText inputLatitude = findViewById(R.id.inputLatitude);
        EditText inputLongitude  = findViewById(R.id.inputLongitude);
        charabutton = findViewById(R.id.charaButton);
        confirmButton = findViewById(R.id.confirm_button);
        TextView errorMessage = findViewById(R.id.errorMessage);

        placeholder.add("Current Temperature: ");
        placeholder.add("Feels like: ");
        placeholder.add("Lowest Temperature: ");
        placeholder.add("Highest Temperature: ");
        temperatures.add("temp");
        temperatures.add("feels_like");
        temperatures.add("temp_min");
        temperatures.add("temp_max");

/*        This butt5on sets the input coordinates to variables that will be used to build url*/
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lati = inputLatitude.getText().toString();
                String longi = inputLongitude.getText().toString();
                latitude = Double.valueOf(lati);
                longitude = Double.valueOf(longi);
            }
        });

/*              1. checks that there is values that can be sent to url
                2. Sends https request and calls weather and makes an api call
                3. parses the JSON object received and displays it in cardview*/
        charabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if else to stop app from crashing in case weather is requested without setting latitude and longitue*/
                if(latitude==null || longitude==null){
                    errorMessage.setText("Error! Please input valid coordinates and press the 'Confirm Coordinates' button^");
                }else{
                    /*url builder class in utils. takes in coordinates and inserts them into HTTPS request placeholder to create url*/
                    String url = Utils.buildURL(latitude, longitude);
                    JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String location = response.getString("name");
                                Log.d("TAG", location);
                                JSONArray weather = response.getJSONArray("weather");
                                JSONObject zero = weather.getJSONObject(0);
                                String description = zero.getString("description");
                                desc.setText(description);
                                JSONObject temps = response.getJSONObject("main");
                                for(int i =0; i<4; i++){
                                    String temp = temperatures.get(i);
                                    double prt = temps.getDouble(temp);
                                    String prnt = Double.toString(prt);
                                    prnt = placeholder.get(i)  + prnt;
                                    prinTemperatures.add(prnt);
                                }
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, prinTemperatures );
                                listView.setAdapter(arrayAdapter);
                                JSONObject sys = response.getJSONObject("sys");
                                String loc = sys.getString("country");
                                textViewLocation.setText("Location: "+location + ", " + loc);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });
    }
}

