package com.example.pruebas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Confirmación");
                    builder.setMessage("Está seguro de eliminar la lista");
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            borrarItems(idLista);
                            borrarLista(idUsuario,idLista);
                            listaCompras=cargarListaComprasActulizada(idUsuario);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return false;
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

    public ArrayList<ComprasPlanificadas> cargarListaComprasActulizada(String idUsuario){
        SQLiteDatabase db=conn.getReadableDatabase();
        ComprasPlanificadas compra=null;
        ArrayList<ComprasPlanificadas> lista=new ArrayList<ComprasPlanificadas>();
        Cursor cursor =db.rawQuery("SELECT * FROM COMPRAS_PLANIFICADAS WHERE CED_USU_COM='"+idUsuario+"'",null);
        while (cursor.moveToNext()){
            compra=new ComprasPlanificadas();
            compra.setNumeroCompra(cursor.getString(0));
            compra.setNombreCompra(cursor.getString(1));
            compra.setCedulaUsuario(cursor.getString(2));
            Log.i("Cate",compra.getNombreCompra());
            lista.add(compra);
        }
        conn.close();
        return lista;
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
