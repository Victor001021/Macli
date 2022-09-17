package com.vitor.alcan.appclima;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CidadesAdapter extends RecyclerView.Adapter<CidadesAdapter.MyViewHolder> {
    private List<Cidade> listaCidades;

    public CidadesAdapter(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    @NonNull
    @Override
    public CidadesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cidades, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CidadesAdapter.MyViewHolder holder, int position) {

        Cidade cidades =listaCidades.get(position);
        holder.tvListaCidades.setText(cidades.getCidade());

    }


    @Override
    public int getItemCount() {
        return this.listaCidades.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvListaCidades;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListaCidades = itemView.findViewById(R.id.tvListaCidades);
        }
    }
}
