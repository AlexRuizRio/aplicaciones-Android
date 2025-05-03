package com.example.persistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter {
    private final LayoutInflater inflater;

    public ContactoAdapter (Context context, List<Contacto> contactos) {
        super(context, 0, contactos);
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView imag;
        TextView nombre;
        TextView telf;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        Contacto contacto = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_contacto, parent, false);
        }
        return convertView;
    }
}
