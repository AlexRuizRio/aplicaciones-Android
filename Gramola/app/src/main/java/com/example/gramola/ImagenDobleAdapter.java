package com.example.gramola;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

public class ImagenDobleAdapter extends BaseAdapter {

    private Context context;
    private List<Cancion> canciones;

    public ImagenDobleAdapter(Context context, List<Cancion> canciones) {
        this.context = context;
        this.canciones = canciones;
    }


    public int getCount() {
        return (int) Math.ceil(canciones.size() / 2.0); // 2 por fila
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // 1) Inflar el layout de 2 imágenes
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);

        // 2) Referenciar los ImageView
        ImageView img1 = v.findViewById(R.id.imagen1);
        ImageView img2 = v.findViewById(R.id.imagen2);

        // 3) Calcular índices de las dos canciones de esta fila
        int idx1 = position * 2;
        int idx2 = idx1 + 1;

        // 4) Objeto Cancion Nº1
        Cancion c1 = canciones.get(idx1);
        // Aquí decides qué imagen mostrar para c1, p.ej:
        img1.setImageResource(c1.getImagenResId());
        // Listener para menú:
        img1.setOnClickListener(view -> mostrarMenu(view, c1));

        // 5) Si hay segunda canción en esta fila:
        if (idx2 < canciones.size()) {
            Cancion c2 = canciones.get(idx2);
            img2.setImageResource(c2.getImagenResId());
            img2.setOnClickListener(view -> mostrarMenu(view, c2));
        } else {
            img2.setVisibility(View.INVISIBLE);
        }

        return v;
    }

    private void mostrarMenu(View anchor, Cancion cancion) {
        PopupMenu popup = new PopupMenu(context, anchor);

        // Opción 1: Mostrar info del disco
        popup.getMenu().add(0, 1, 0, "Información");
        // Opción 2: Ir a la URL
        popup.getMenu().add(0, 2, 1, "Ir a página web");

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == 1) {
                // CONSTRUIR EL TOAST CON TODOS LOS CAMPOS DE Cancion
                String info =  "Título: " + cancion.getTitulo() + "\n"
                        + "Autor: "  + cancion.getAutor()  + "\n"
                        + "Disco: "  + cancion.getDisco()  + "\n"
                        + "Año: "    + cancion.getAnio();
                Toast.makeText(context, info, Toast.LENGTH_LONG).show();
                return true;
            }
            if (item.getItemId() == 2) {
                // LANZAR NAVEGADOR A cancion.getUrl()
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(cancion.getUrl()));
                context.startActivity(i);
                return true;
            }
            return false;
        });

        popup.show();
    }

}
