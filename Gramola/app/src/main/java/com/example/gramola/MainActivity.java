package com.example.gramola;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        List<Cancion> canciones = new ArrayList<>();
        canciones.add(new  Cancion("Smells Like Teen Spirit", "Nirvana", "Nevermind", 1991,
                "https://es.wikipedia.org/wiki/Nevermind", R.drawable.nirvana));
        canciones.add(new Cancion("Time", "Pink Floyd", "The Dark Side of the Moon", 1973,
                        "https://es.wikipedia.org/wiki/The_Dark_Side_of_the_Moon", R.drawable.pinkfloid));
        canciones.add(new Cancion("Salta!!", "Tequila", "Rock and Roll", 1981,
                        "https://es.wikipedia.org/wiki/Tequila_(banda)", R.drawable.tequila));
        canciones.add(new Cancion("Come Together", "The Beatles", "Abbey Road", 1969,
                        "https://es.wikipedia.org/wiki/Abbey_Road", R.drawable.beatle));



        ImagenDobleAdapter adapter = new ImagenDobleAdapter(this, canciones);
        listView.setAdapter(adapter);

        //listView.setOnItemClickListener((parent, view, position, id) -> {
          //  Toast.makeText(this, "Hiciste clic en: " + items[position], Toast.LENGTH_SHORT).show();
        //});
    }
}