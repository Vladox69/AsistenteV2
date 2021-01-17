package com.example.pruebas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebas.entidades.ComprasPlanificadas;

import java.util.ArrayList;

public class adapterListas extends RecyclerView.Adapter<adapterListas.ViewHolderListas> implements View.OnClickListener {

    public adapterListas(ArrayList<ComprasPlanificadas> listaCompras) {
        this.listaCompras = listaCompras;
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
