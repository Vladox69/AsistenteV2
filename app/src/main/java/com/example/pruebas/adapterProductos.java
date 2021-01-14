package com.example.pruebas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebas.entidades.Producto;

import java.util.ArrayList;

public class adapterProductos extends RecyclerView.Adapter<adapterProductos.ViewHolderProductos> {
    ArrayList<Producto> ListProductos;

    public adapterProductos(ArrayList<Producto> listProductos) {
        ListProductos = listProductos;
    }

    @NonNull
    @Override
    public adapterProductos.ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,null,false);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterProductos.ViewHolderProductos holder, int position) {
        holder.asignarProductos(ListProductos.get(position));
        holder.nombreProductos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                ListProductos.get(position).setSeleccion(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListProductos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder {
        CheckBox nombreProductos;
        public ViewHolderProductos(@NonNull View itemView) {
            super(itemView);
            nombreProductos=(CheckBox) itemView.findViewById(R.id.checkNombreProducto);
        }

        public void asignarProductos(Producto producto) {
        nombreProductos.setText(producto.getNombreProducto());
        nombreProductos.setChecked(producto.isSeleccion());
        }
    }
}