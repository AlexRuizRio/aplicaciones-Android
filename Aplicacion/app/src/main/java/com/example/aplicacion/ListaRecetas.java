package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ListaRecetas extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Receta> recetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listview);

        // Recuperar la lista del Intent
        recetas = (ArrayList<Receta>) getIntent().getSerializableExtra("recetas");

        RecetaAdapter adapter = new RecetaAdapter(this, recetas);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.v_general || id == R.id.v_ayuda) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("fragment", id); // pasa el ID del fragmento que quieres mostrar
            startActivity(intent);
            finish(); // opcional: para cerrar ListaRecetas
            return true;
        } else if (id == R.id.apagar) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
