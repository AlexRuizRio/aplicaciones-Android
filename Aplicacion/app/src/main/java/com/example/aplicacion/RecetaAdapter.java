package com.example.aplicacion;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecetaAdapter extends BaseAdapter {
    private Context context;
    private List<Receta> recetas;

    public RecetaAdapter(Context context, List<Receta> recetas) {
        this.context = context;
        this.recetas = recetas;
    }

    @Override
    public int getCount() {
        return recetas.size();
    }

    @Override
    public Object getItem(int position) {
        return recetas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Receta receta = recetas.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_receta, parent, false);
        }

        TextView nombre = convertView.findViewById(R.id.textView3);
        ImageView imagen = convertView.findViewById(R.id.imageView3);
        ImageView iconoFavorito = convertView.findViewById(R.id.imageView4); // tu botón de favorito

        nombre.setText(receta.getNombre());
        Picasso.get().load(receta.getImagenUrl()).into(imagen);

        // Recuperar SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        boolean esFavorita = prefs.getBoolean(receta.getNombre(), false); // usa nombre o ID único

        // Mostrar el ícono correcto
        if (esFavorita) {
            iconoFavorito.setImageResource(R.drawable.favorite); // icono de favorito activado
        } else {
            iconoFavorito.setImageResource(R.drawable.favorite_border); // icono desactivado
        }

        // Al hacer clic en la estrella
        iconoFavorito.setOnClickListener(v -> {
            boolean nuevoEstado = !prefs.getBoolean(receta.getNombre(), false);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(receta.getNombre(), nuevoEstado);
            editor.apply();

            // Cambiar imagen en pantalla
            if (nuevoEstado) {
                iconoFavorito.setImageResource(R.drawable.favorite);
            } else {
                iconoFavorito.setImageResource(R.drawable.favorite_border);
            }
        });

        return convertView;
    }
}
