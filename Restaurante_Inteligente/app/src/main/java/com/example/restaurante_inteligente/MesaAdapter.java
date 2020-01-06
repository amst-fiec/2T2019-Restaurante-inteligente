package com.example.restaurante_inteligente;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MesaViewHolder> {

    //private final List<Mesa> items;

    public class MesaViewHolder extends RecyclerView.ViewHolder {
        //final ImageView imagen;
        //final TextView id;
        //final TextView capacidad;
        //final TextView estado;

        public MesaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public MesaAdapter.MesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MesaAdapter.MesaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
