package com.example.persistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {
    private final LayoutInflater inflater;

    public ContactoAdapter (Context context, List<Contacto> contactos) {
        super(context, 0, contactos);
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView imag;
        TextView nombre;
        TextView desc;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        Contacto contacto = (Contacto) getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contacto_list, parent, false);
            holder = new ViewHolder();
            holder.imag = convertView.findViewById(R.id.imagen);
            holder.nombre = convertView.findViewById(R.id.nombre);
            holder.desc = convertView.findViewById(R.id.descripcion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (contacto != null) {
            holder.imag.setImageResource(contacto.getImagenId());
            holder.nombre.setText(contacto.getNombre());
            holder.desc.setText(contacto.getDescripcion());
        }
        return convertView;
    }
}
