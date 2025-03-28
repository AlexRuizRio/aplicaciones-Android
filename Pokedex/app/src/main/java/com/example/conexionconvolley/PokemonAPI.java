package com.example.conexionconvolley;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

class PokemonAPI {
    private Context context;
    private ImageView imageView;
    private Spinner spinner;
    private int nPokemonId = 1;
    private TextView pokeName;
    private RequestQueue requestQueue;

    public PokemonAPI (Context context, ImageView imageView, Spinner spinner, TextView pokeName) {
        this.context = context;
        this.imageView = imageView;
        this.spinner = spinner;
        this.pokeName = pokeName;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void cargarPokemon(int id) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + id + "/";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("API_RESPONSE", response.toString());

                            JSONObject sprites = response.getJSONObject("sprites");
                            String imageUrl = sprites.getString("front_default");

                            JSONArray formsArray = response.getJSONArray("forms");
                            String name = formsArray.getJSONObject(0).getString("name");
                            name = name.toUpperCase();
                            pokeName.setText(name);

                            Picasso.get().load(imageUrl).into(imageView);


                            JSONArray abilitiesArray = response.getJSONArray("abilities");


                            List<String> habilidades = new ArrayList<>();
                            for (int i = 0; i < abilitiesArray.length(); i++) {
                                JSONObject abilityObject = abilitiesArray.getJSONObject(i);
                                JSONObject ability = abilityObject.getJSONObject("ability");
                                habilidades.add(ability.getString("name"));
                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    context, android.R.layout.simple_spinner_item, habilidades
                            );
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(jsonObjectRequest);
    }
    public void cargarSigPokemon() {
        nPokemonId++;
        cargarPokemon(nPokemonId);
    }

}
