package com.example.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentoReNew extends Fragment {

    public FragmentoReNew () {}


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_recipe_new, container, false);

        EditText nombre = view.findViewById(R.id.editTextText);
        EditText descrip = view.findViewById(R.id.editTextText2);
        Button save = view.findViewById(R.id.button);

        save.setOnClickListener(v -> {
            String nombreS = nombre.getText().toString();
            String descripcionS = descrip.getText().toString();

            RecetasDBHelper dbHelpet = new RecetasDBHelper(getActivity());
            long recetaId = dbHelpet.insertRecetas(nombreS, descripcionS);

            SharedPreferences prefs = getActivity().getSharedPreferences("Recetaprefs", Context.MODE_PRIVATE);
            prefs.edit().putLong("ultima_receta_guardada_id", recetaId).apply();
        });
        return view;
    }
}
