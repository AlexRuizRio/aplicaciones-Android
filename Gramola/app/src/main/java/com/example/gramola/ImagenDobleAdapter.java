package com.example.gramola;

import android.content.Context;
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
    private List<Integer> imagenes;

    public ImagenDobleAdapter(Context context, List<Integer> imagenes) {
        this.context = context;
        this.imagenes = imagenes;
    }


    public int getCount() {
        return (int) Math.ceil(imagenes.size() / 2.0); // 2 por fila
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        ImageView img1 = itemView.findViewById(R.id.imagen1);
        ImageView img2 = itemView.findViewById(R.id.imagen2);

        int index1 = position * 2;
        int index2 = index1 + 1;

        img1.setImageResource(imagenes.get(index1));
        img1.setOnClickListener(v -> mostrarMenu(v, index1));

        if (index2 < imagenes.size()) {
            img2.setImageResource(imagenes.get(index2));
            img2.setOnClickListener(v -> mostrarMenu(v, index2));
        } else {
            img2.setVisibility(View.INVISIBLE); // Si hay número impar, ocultamos la segunda
        }
        return itemView;
    }

    private void mostrarMenu(View anchorView, int imagenIndex) {
        PopupMenu popup = new PopupMenu(context, anchorView);
        popup.getMenu().add("Informacion");
        popup.getMenu().add("Ir a página web");

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getTitle().toString()) {
                case "Informacion":
                    Toast.makeText(context, "Informacion " + imagenIndex, Toast.LENGTH_SHORT).show();
                    return true;
                case "Ir a página web":
                    Toast.makeText(context, "Ir a página web " + imagenIndex, Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });

        popup.show();
    }
}
