package com.vitor.alcan.appclima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class HoraAdapter extends RecyclerView.Adapter<HoraAdapter.ViewHolder2> {
    private Context context;
    private ArrayList<DadosHora> dadosHoraArrayList;
    private Calendar c;
    private int sHour;

    public HoraAdapter(Context context, ArrayList<DadosHora> dadosHoraArrayList) {
        this.context = context;
        this.dadosHoraArrayList = dadosHoraArrayList;
    }


    @NonNull
    @Override
    public HoraAdapter.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_dia_hora, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoraAdapter.ViewHolder2 holder, int position) {
        DadosHora dadosHora = dadosHoraArrayList.get(position);
        c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, position);
        sHour = c.get(Calendar.HOUR_OF_DAY);


        holder.tv_dia_hora.setText(String.valueOf(sHour) + ":00");
        //holder.tvCondicao_dia_hora.setText(dadosHora.getDescricao());
        holder.tvTemp_dia_hora.setText(dadosHora.getTemp() + " °C");
        String icone = dadosHora.getIcone();

     /*   Picasso.with(context)
                .load("https://openweathermap.org/img/wn/" +icone+ "@4x.png")
                .into(holder.iv_dia_hora);*/

        Picasso.get()
                .load("http://openweathermap.org/img/wn/" +icone+ "@4x.png")
                .into(holder.iv_dia_hora);




    }

    @Override
    public int getItemCount() {
        return dadosHoraArrayList.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{
        private TextView tv_dia_hora, tvTemp_dia_hora;
        private ImageView iv_dia_hora;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            tv_dia_hora = itemView.findViewById(R.id.tv_dia_hora);
            //tvCondicao_dia_hora = itemView.findViewById(R.id.tvCondicao_dia_hora);
            tvTemp_dia_hora = itemView.findViewById(R.id.tvTemp_dia_hora);
            iv_dia_hora = itemView.findViewById(R.id.iv_dia_hora);

        }
    }
}
