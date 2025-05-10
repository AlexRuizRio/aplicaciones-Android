package com.example.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
        ListView listViewm = findViewById(R.id.listacontactos);

        registerForContextMenu(listViewm);

        btnadd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarContactoActivity.class);
            startActivity(intent);
        });
        btnoff.setOnClickListener(v -> {
            finishAffinity();
        });

        listaContactos = findViewById(R.id.listacontactos);
        dbHelper = new ContactoDBHelper(this);
        cargarContactos();

    }

    private void cargarContactos() {
        List<Contacto> contactos = dbHelper.obtenerContactos();
        contactoAdapter = new ContactoAdapter(this, contactos);
        listaContactos.setAdapter(contactoAdapter);
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume llamado");
        super.onResume();
        cargarContactos();  // Recarga por si se han añadido/actualizado contactos
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (contactoAdapter != null) {
            Contacto contacto = contactoAdapter.getItem(contactoAdapter.getSelectedPosition());
            if (item.getItemId() == 1) {
                // Editar
                Intent intent = new Intent(this, EditarContactoActivity.class);
                intent.putExtra("id", contacto.getId());
                startActivity(intent);
                return true;
            } else if (item.getItemId() == 2) {
                // Eliminar
                contactoAdapter.getDbHelper().eliminarContacto(contacto.getId());
                contactoAdapter.remove(contacto);
                contactoAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }


}
