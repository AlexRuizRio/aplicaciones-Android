package com.example.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarContactoActivity extends AppCompatActivity {

    private EditText editNombre, editDescripcion;
    private Button btnGuardar, btnAtras;
    private ContactoDBHelper dbHelper;
    private int contactoId;
    private Contacto contactoActual;
    private  int imagenActualIndice;
    private int[] imagenes = {
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3,
            R.drawable.foto4,
            R.drawable.foto5,
            R.drawable.foto6,
            R.drawable.foto7,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_datos);

        editNombre = findViewById(R.id.editarnombre);
        editDescripcion = findViewById(R.id.editarintereses);
        btnGuardar = findViewById(R.id.salvarEditar);
        btnAtras = findViewById(R.id.atrasEditar);
        ImageView imageView = findViewById(R.id.imageView);

        dbHelper = new ContactoDBHelper(this);

        // Obtener el ID pasado desde el intent
        contactoId = getIntent().getIntExtra("id", -1);
        if (contactoId == -1) {
            Toast.makeText(this, "ID de contacto no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Cargar los datos del contacto
        contactoActual = dbHelper.obtenerContactoPorId(contactoId);
        imageView.setImageResource(contactoActual.getImagenId());
        if (contactoActual != null) {
            editNombre.setText(contactoActual.getNombre());
            editDescripcion.setText(contactoActual.getDescripcion());
        }
        int imagenActualId = contactoActual.getImagenId();
        imagenActualIndice = 0;

        // Buscar el índice correspondiente al ID
                for (int i = 0; i < imagenes.length; i++) {
                    if (imagenes[i] == imagenActualId) {
                        imagenActualIndice = i;
                        break;
                    }
                }

        imageView.setImageResource(imagenes[imagenActualIndice]);

        imageView.setOnClickListener(v -> {
            imagenActualIndice = (imagenActualIndice + 1) % imagenes.length;
            imageView.setImageResource(imagenes[imagenActualIndice]);

            // Opcional: actualizar el campo del contacto
            contactoActual.setImagenid(imagenes[imagenActualIndice]);
        });

        btnGuardar.setOnClickListener(v -> {
            String nuevoNombre = editNombre.getText().toString().trim();
            String nuevaDescripcion = editDescripcion.getText().toString().trim();

            if (nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty()) {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show();
                return;
            }

            contactoActual.setNombre(nuevoNombre);
            contactoActual.setDescripcion(nuevaDescripcion);

            dbHelper.actualizarContacto(contactoActual);
            Toast.makeText(this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
            finish(); // volver al MainActivity
        });

        btnAtras.setOnClickListener(v -> finish());
    }
}
