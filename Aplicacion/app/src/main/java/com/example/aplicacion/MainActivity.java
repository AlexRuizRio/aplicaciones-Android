package com.example.aplicacion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;
import org.json.JSONArray;
import org.json.JSONObject;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private ArrayList<Receta> todasLasRecetas = new ArrayList<>();
    private TextView txtResultado; // Debes tener un TextView en activity_main.xml con este id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentSup fragsup = new FragmentSup();
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        cargarRecetasDesdeAPI();
        Bundle args = new Bundle();
        args.putSerializable("recetas", todasLasRecetas); // la clave puede ser cualquier string
        fragsup.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_sup, fragsup).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_inf, new FragmentInf()).commit();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment frag = null;

        if(id == R.id.v_general) {
            frag = new FragmentSup();
        } else if (id == R.id.v_ayuda) {
            frag = new FragmentHelp();
        } else if (id == R.id.apagar) {
            finish();
        }
        if (frag != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_sup, frag).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void cargarRecetasDesdeAPI() {
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s="; // Devuelve muchas recetas

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray mealsArray = response.getJSONArray("meals");
                        todasLasRecetas.clear(); // limpia antes de volver a cargar

                        for (int i = 0; i < mealsArray.length(); i++) {
                            JSONObject meal = mealsArray.getJSONObject(i);
                            String nombre = meal.getString("strMeal");
                            String imagen = meal.getString("strMealThumb");

                            todasLasRecetas.add(new Receta(nombre, imagen));
                        }
                    } catch (Exception e) {
                        txtResultado.setText("Error al procesar datos");
                    }
                },
                error -> txtResultado.setText("Error al conectar con API")
        );

        requestQueue.add(request);
    }
}