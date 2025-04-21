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

        List<Integer> listaImagenes = new ArrayList<>();
        listaImagenes.add(R.drawable.beatle);
        listaImagenes.add(R.drawable.nirvana);
        listaImagenes.add(R.drawable.pinkfloid);
        listaImagenes.add(R.drawable.tequila);

        ImagenDobleAdapter adapter = new ImagenDobleAdapter(this, listaImagenes);
        listView.setAdapter(adapter);

        //listView.setOnItemClickListener((parent, view, position, id) -> {
          //  Toast.makeText(this, "Hiciste clic en: " + items[position], Toast.LENGTH_SHORT).show();
        //});
    }
}