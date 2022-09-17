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

public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder3> {
    private ArrayList<DadosClimaDias> dadosClimaDiasArrayList;
    private Context context;

    public DiasAdapter(ArrayList<DadosClimaDias> dadosClimaDiasArrayList, Context context) {
        this.dadosClimaDiasArrayList = dadosClimaDiasArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_dia_hora, parent, false);

        return new DiasAdapter.ViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder3 holder, int position) {
        /*Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String diaSemana="";
        day+=1;

        if (day > 7) {
            day = 1;
        }

        if(day == 0){
            diaSemana = "Hoje";
        }else {
            switch (day) {
                case Calendar.SUNDAY:
                    diaSemana = "Dom";
                    break;
                case Calendar.MONDAY:
                    diaSemana = "Seg";
                    break;
                case Calendar.TUESDAY:
                    diaSemana = "Ter";
                    break;

                case Calendar.WEDNESDAY:
                    diaSemana = "Qua";
                    break;

                case Calendar.THURSDAY:
                    diaSemana = "Qui";
                    break;

                case Calendar.FRIDAY:
                    diaSemana = "Sex";
                    break;

                case Calendar.SATURDAY:
                    diaSemana = "Sáb";
                    break;
            }
        }*/

        DadosClimaDias dadosClimaDias = dadosClimaDiasArrayList.get(position);
        holder.tv_dia_hora.setText(dadosClimaDias.getDia());
        holder.tvTemp_dia_hora.setText(dadosClimaDias.getTempMax() + "° / " + dadosClimaDias.getTempMin() + "°");
        String icone = dadosClimaDias.getIcone();

        Picasso.with(context)
                .load("https://openweathermap.org/img/wn/" +icone+ "@4x.png")
                .into(holder.iv_dia_hora);

    }

    @Override
    public int getItemCount() {
        return dadosClimaDiasArrayList.size();
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private TextView tv_dia_hora, tvTemp_dia_hora;
        private ImageView iv_dia_hora;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            tv_dia_hora = itemView.findViewById(R.id.tv_dia_hora);
            //tvCondicao_dia_hora = itemView.findViewById(R.id.tvCondicao_dia_hora);
            tvTemp_dia_hora = itemView.findViewById(R.id.tvTemp_dia_hora);
            iv_dia_hora = itemView.findViewById(R.id.iv_dia_hora);
        }
    }
}
