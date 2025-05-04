package com.example.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listaContactos;
    private ContactoAdapter contactoAdapter;
    private ContactoDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView btnadd = findViewById(R.id.guardar);
        ImageView btnoff = findViewById(R.id.apagar);
       btnadd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarContactoActivity.class);
            startActivity(intent);
        });
        btnoff.setOnClickListener(v -> {
            finishAffinity();
        });
        /*
        btnadd.setOnClickListener(v ->
                Toast.makeText(this, "Pulsaste Guardar", Toast.LENGTH_SHORT).show()
        );

        btnoff.setOnClickListener(v ->
                Toast.makeText(this, "Pulsaste Apagar", Toast.LENGTH_SHORT).show()
        );
        */
        listaContactos = findViewById(R.id.listacontactos);
        dbHelper = new ContactoDBHelper(this);

        cargarContactos();

        // Ejemplo: abre una actividad para agregar contacto
        // findViewById(R.id.botonAgregar).setOnClickListener(v -> {
        //     Intent intent = new Intent(this, AgregarContactoActivity.class);
        //     startActivity(intent);
        // });
    }

    private void cargarContactos() {
        List<Contacto> contactos = dbHelper.obtenerContactos();
        contactoAdapter = new ContactoAdapter(this, contactos);
        listaContactos.setAdapter(contactoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarContactos();  // Recarga por si se han añadido/actualizado contactos
    }
}
