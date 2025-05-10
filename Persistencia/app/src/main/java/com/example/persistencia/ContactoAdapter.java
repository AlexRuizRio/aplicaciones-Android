package com.example.persistencia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {

    private final LayoutInflater inflater;
    private Context context;
    private List<Contacto> contactos;
    private ContactoDBHelper dbHelper;
    private int selectedPosition = -1;


    public int getSelectedPosition() {
        return selectedPosition;
    }

    public ContactoDBHelper getDbHelper() {
        return dbHelper;
    }

    public ContactoAdapter(Context context, List<Contacto> contactos) {
        super(context, 0, contactos);
        this.context = context;
        this.contactos = contactos;
        this.inflater = LayoutInflater.from(context);
        this.dbHelper = new ContactoDBHelper(context);
    }

    static class ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        TextView tvDescripcion;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Contacto contacto = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contacto_list, parent, false);
            holder = new ViewHolder();
            holder.ivImagen = convertView.findViewById(R.id.imagen);
            holder.tvNombre = convertView.findViewById(R.id.nombre);
            holder.tvDescripcion = convertView.findViewById(R.id.descripcion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (contacto != null) {
            holder.ivImagen.setImageResource(contacto.getImagenId());
            holder.tvNombre.setText(contacto.getNombre());
            holder.tvDescripcion.setText(contacto.getDescripcion());
        }

        holder.ivImagen.setOnClickListener(v -> {
            selectedPosition = position;

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Opciones")
                    .setItems(new CharSequence[]{"Editar", "Eliminar"}, (dialog, which) -> {
                        if (which == 0) {
                            Intent intent = new Intent(context, EditarContactoActivity.class);
                            intent.putExtra("id", contacto.getId());
                            context.startActivity(intent);
                        } else {
                            dbHelper.eliminarContacto(contacto.getId());
                            contactos.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.show();
        });

        return convertView;
    }

}