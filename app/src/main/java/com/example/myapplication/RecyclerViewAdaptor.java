package com.example.myapplication;

import java.util.List;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView comida, descripcion;
        ImageView fotoComida;

        public ViewHolder(View itemView) {
            super(itemView);
            comida = (TextView) itemView.findViewById(R.id.tvComida);
            descripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            fotoComida = (ImageView) itemView.findViewById(R.id.imageButton);
        }


    }
    public List<Comida> comidaList;

    public RecyclerViewAdaptor(List<Comida> comidaList) {
        this.comidaList = comidaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comida,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.comida.setText(comidaList.get(position).getComida());
        holder.descripcion.setText(comidaList.get(position).getDescripcion());
        holder.fotoComida.setImageResource(comidaList.get(position).getImgComida());
    }

    @Override
    public int getItemCount() {
        return comidaList.size();
    }
}
