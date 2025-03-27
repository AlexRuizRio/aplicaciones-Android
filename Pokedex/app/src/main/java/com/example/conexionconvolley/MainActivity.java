package com.example.conexionconvolley;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

        private PokemonAPI pokemonAPI;
        private ImageView imageView;
        private Spinner spinner;
        private TextView name;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageView = findViewById(R.id.imageViewPokemon);
            spinner = findViewById(R.id.spinnerAbilities);
            name = findViewById(R.id.nombre);


            pokemonAPI = new PokemonAPI(this, imageView, spinner,name);
            pokemonAPI.cargarPokemon(1);

            imageView.setOnClickListener(view -> pokemonAPI.cargarSigPokemon());
        }

}