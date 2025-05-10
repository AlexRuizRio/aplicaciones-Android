package com.example.persistencia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarContactoActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText descripcionEditText;
    private Button guardarButton;
    private Button atrasButton;
    private ContactoDBHelper dbHelper;
    private ImageView imagenView;

    private int[] imagenes = {
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3,
            R.drawable.foto4,
            R.drawable.foto5,
            R.drawable.foto6,
            R.drawable.foto7,
    };

    private int indiceImagenActual = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada_datos); // Asegúrate que se llama así tu XML

        nombreEditText = findViewById(R.id.nombrecreate);
        descripcionEditText = findViewById(R.id.descripcioncreate);
        imagenView = findViewById(R.id.imagencreate);
        Button guardarButton = findViewById(R.id.guardarcreate);
        Button salirButton = findViewById(R.id.atrascreate);

        dbHelper = new ContactoDBHelper(this);

        // Mostrar primera imagen
        imagenView.setImageResource(imagenes[indiceImagenActual]);

        // Cambiar imagen al pulsar sobre ella
        imagenView.setOnClickListener(v -> {
            indiceImagenActual = (indiceImagenActual + 1) % imagenes.length;
            imagenView.setImageResource(imagenes[indiceImagenActual]);
        });

        // Guardar contacto
        guardarButton.setOnClickListener(v -> {
            String nombre = nombreEditText.getText().toString().trim();
            String descripcion = descripcionEditText.getText().toString().trim();

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Contacto nuevoContacto = new Contacto(nombre, descripcion, imagenes[indiceImagenActual]);
            dbHelper.insertarContacto(nuevoContacto);

            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
            finish(); // Cierra la actividad y vuelve al MainActivity
        });

        // Salir sin guardar
        salirButton.setOnClickListener(v -> {
            finish(); // Cierra la actividad sin guardar
        });
    }
}
