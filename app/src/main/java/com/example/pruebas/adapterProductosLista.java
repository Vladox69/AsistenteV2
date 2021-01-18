package com.example.pruebas;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebas.entidades.Producto;

import java.util.ArrayList;

public class adapterProductosLista extends RecyclerView.Adapter<adapterProductosLista.ViewHolderProductosLista> {

    ArrayList<Producto> ListProductos;
    Activity activity;
    String idLista;

    public adapterProductosLista(ArrayList<Producto> listProductos, Activity activity) {
        this.ListProductos = listProductos;
        this.activity = activity;
    }



    @NonNull
    @Override
    public adapterProductosLista.ViewHolderProductosLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Bundle parametros = activity.getIntent().getExtras();
        idLista = parametros.getString("iddetalle");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos_lista,null,false);
        return new ViewHolderProductosLista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterProductosLista.ViewHolderProductosLista holder, int position) {
        holder.asignarProductosLista(ListProductos.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String idProducto=ListProductos.get(position).getNombreProducto();
                borrarItem(idLista,idProducto);
                return true;
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

    public class ViewHolderProductosLista extends RecyclerView.ViewHolder {
        TextView nombreProductos;

        public ViewHolderProductosLista(@NonNull View itemView) {
            super(itemView);
            nombreProductos=(TextView) itemView.findViewById(R.id.txtProductosLista);
        }

        public void asignarProductosLista(Producto producto) {
            nombreProductos.setText(producto.getNombreProducto());
        }

    }
}
