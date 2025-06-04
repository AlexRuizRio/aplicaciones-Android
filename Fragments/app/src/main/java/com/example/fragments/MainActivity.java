package com.example.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.example.fragments.R; // ✅ ESTE ES EL CORRECTO


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragsup, new FragmentoSup())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fraginf, new FragmentoInf())
                .commit();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Asegúrate de que el archivo se llama así
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        // En lugar de switch/case, usamos if/else:
        if (id == R.id.action_favorito) {
            fragment = new FragmentoFav();
        } else if (id == R.id.action_nueva) {
            fragment = new FragmentoReNew();
        } else if (id == R.id.action_explorar) {
            fragment = new FragmentoReEx();
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fraginf, fragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}