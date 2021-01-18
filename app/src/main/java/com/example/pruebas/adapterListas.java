package com.example.pruebas;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebas.entidades.ComprasPlanificadas;

import java.util.ArrayList;

public class adapterListas extends RecyclerView.Adapter<adapterListas.ViewHolderListas> implements View.OnClickListener {
    Activity activity;
    public adapterListas(ArrayList<ComprasPlanificadas> listaCompras,Activity activity) {
        this.listaCompras = listaCompras;
        this.activity=activity;
    }

    ArrayList<ComprasPlanificadas> listaCompras;
    private View.OnClickListener listener;
    @NonNull
    @Override
    public ViewHolderListas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nombre_lista,null,false);
        view.setOnClickListener(this);
        return new ViewHolderListas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListas holder, int position) {
    holder.asignarComprasPlanificadas(listaCompras.get(position));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String idLista=listaCompras.get(position).getNumeroCompra();
            Intent intProductosLista =new Intent(activity,productosLista.class);
            intProductosLista.putExtra("iddetalle",idLista);
            activity.startActivity(intProductosLista);
        }
    });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String idLista=listaCompras.get(position).getNumeroCompra();
                String idUsuario=listaCompras.get(position).getCedulaUsuario();
                borrarItems(idLista);
                borrarLista(idUsuario,idLista);
                return true;
            }
        });

    }

    ConexionSQLiteHelper conn;
    private void borrarItems(String idlista) {
        conn=new ConexionSQLiteHelper(activity);
        SQLiteDatabase db=conn.getReadableDatabase();
        String queryDeleteDetalle = "DELETE FROM DETALLE_COMPRAS WHERE NUM_COM_DET = '" + idlista + "'";
        db.execSQL(queryDeleteDetalle);
        db.close();
    }
    private  void borrarLista(String idUsuario,String idLista){
        conn=new ConexionSQLiteHelper(activity);
        SQLiteDatabase db=conn.getReadableDatabase();
        String queryDeleteDetalle = "DELETE FROM COMPRAS_PLANIFICADAS WHERE CED_USU_COM = '" + idUsuario + "' AND NUM_COM = '"+idLista+"'";
        db.execSQL(queryDeleteDetalle);
        db.close();

    }

    @Override
    public int getItemCount() {
        return listaCompras.size();
    }

    public void setOnclickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
    if(listener!=null){
        listener.onClick(v);
    }
    }

    public class ViewHolderListas extends RecyclerView.ViewHolder {

        TextView nombreLista;

        public ViewHolderListas(@NonNull View itemView) {
            super(itemView);
            nombreLista=(TextView)itemView.findViewById(R.id.txtVNombreListas);
        }

        public void asignarComprasPlanificadas(ComprasPlanificadas compra) {
            nombreLista.setText(compra.getNombreCompra());
        }
    }
}
