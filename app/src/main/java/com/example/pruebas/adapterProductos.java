package com.example.pruebas;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    Activity activity;
    String idLista;

    public adapterProductos(ArrayList<Producto> listProductos,Activity activity) {
        ListProductos = listProductos;
        this.activity=activity;
    }

    @NonNull
    @Override
    public adapterProductos.ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Bundle parametros = activity.getIntent().getExtras();
        idLista = parametros.getString("iddetalle");
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
                ListProductos.get(position).setSeleccion(isChecked);
            }
        });
    }

    ConexionSQLiteHelper conn;
    private void borrarItem(String idlista,String idproducto) {
        conn=new ConexionSQLiteHelper(activity);
        SQLiteDatabase db=conn.getReadableDatabase();
        String queryDeleteDetalle = "DELETE FROM DETALLE_COMPRAS WHERE NUM_COM_DET = '" + idlista + "' AND NOM_PRO_DET = '"+idproducto+"'";
        db.execSQL(queryDeleteDetalle);
        db.close();
        notifyDataSetChanged();
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
