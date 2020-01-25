package com.grupo2.restaurante_inteligente;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MesaViewHolder> {

    List<Mesa> items;

    public MesaAdapter(List<Mesa> items) {
        this.items = items;
    }

    public class MesaViewHolder extends RecyclerView.ViewHolder {
        final ImageView imagen;
        final TextView id;
        final TextView capacidad;
        final TextView estado;

        public MesaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ImgLogo);
            id =  itemView.findViewById(R.id.txtMesaID);
            capacidad =  itemView.findViewById(R.id.txtCapacidad);
            estado =  itemView.findViewById(R.id.txtEstado);
        }

        public void asignarDatos(Mesa mesa) {
            imagen.setImageResource(mesa.getImagen());
            id.setText("    Mesa: "+mesa.getId());
            capacidad.setText("     Capacidad:"+mesa.getCapacidad());
            if(mesa.getEstado().equals(false)){
                estado.setTextColor(Color.rgb(255,50,50));
            }else{
                estado.setTextColor(Color.rgb(50,255,50));
            }
            estado.setText("        "+mesa.getEstado());
        }
    }

    @NonNull
    @Override
    public MesaAdapter.MesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mesa,null,false);
        return new MesaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesaAdapter.MesaViewHolder holder, int position) {
        holder.asignarDatos(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
