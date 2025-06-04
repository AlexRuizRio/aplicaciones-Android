package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentSup extends Fragment {

    private ArrayList<Receta> listaRecetas = new ArrayList<>();

    public FragmentSup () {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sup, container, false);

        // Obtener argumentos
        Bundle args = getArguments();
        if (args != null && args.containsKey("recetas")) {
            listaRecetas = (ArrayList<Receta>) args.getSerializable("recetas");
        }

        EditText editText = view.findViewById(R.id.editTextText);
        Button botonFiltrar = view.findViewById(R.id.button);

        botonFiltrar.setOnClickListener(v -> {
            String texto = editText.getText().toString();

            Intent intent = new Intent(getActivity(), ListaRecetas.class);
            intent.putExtra("recetas", listaRecetas);
            intent.putExtra("filtro", texto); // por si más adelante quieres usarlo
            startActivity(intent);
        });
        return view;
    }
}
