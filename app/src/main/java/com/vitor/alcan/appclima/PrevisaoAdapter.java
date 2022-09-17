package com.vitor.alcan.appclima;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class PrevisaoAdapter extends RecyclerView.Adapter<PrevisaoAdapter.ViewHolder>{
    private Context context;
    private ArrayList<DadosClima> dadosClimaArrayList;

    public PrevisaoAdapter(Context context, ArrayList<DadosClima> dadosClimaArrayList) {
        this.context = context;
        this.dadosClimaArrayList = dadosClimaArrayList;
    }

    @NonNull
    @Override
    public PrevisaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_previsao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrevisaoAdapter.ViewHolder holder, int position) {
        DadosClima dadosClima = dadosClimaArrayList.get(position);
        holder.tvDescricao.setText(dadosClima.getDescricao());
        holder.tvTemp.setText(dadosClima.getTemp());
        holder.tvDia.setText(dadosClima.getDia());

        String icone = dadosClima.getIcone();

        Picasso.with(context)
                .load("https://openweathermap.org/img/wn/" +icone+ "@4x.png")
                .into(holder.ivIcon);

        /*
        if(icone.equals("01d")){
            holder.ivIcon.setImageResource(R.drawable.icone01dl);
        }else if(icone.equals("01n")){
            holder.ivIcon.setImageResource(R.drawable.icone01nl);
        }else if(icone.equals("02d")){
            holder.ivIcon.setImageResource(R.drawable.icone02dl);
        }else if(icone.equals("02n")){
            holder.ivIcon.setImageResource(R.drawable.icone02nl);
        }else if(icone.equals("03d") || icone.equals("03n")){
            holder.ivIcon.setImageResource(R.drawable.icone03l);
        }else if(icone.equals("04d") || icone.equals("04n")){
            holder.ivIcon.setImageResource(R.drawable.icone04l);
        }else if(icone.equals("09d") || icone.equals("09n")){
            holder.ivIcon.setImageResource(R.drawable.icone09large);
        }else if(icone.equals("10d")){
            holder.ivIcon.setImageResource(R.drawable.icone010dl);
        }else if(icone.equals("10n")){
            holder.ivIcon.setImageResource(R.drawable.icone010nl);
        }else if(icone.equals("11d") || icone.equals("11n")){
            holder.ivIcon.setImageResource(R.drawable.icone11dlarge);
        }else if(icone.equals("13d") || icone.equals("13n")){
            holder.ivIcon.setImageResource(R.drawable.icone13large);
        }else if(icone.equals("50d") || icone.equals("50n")){
            holder.ivIcon.setImageResource(R.drawable.icone50large);
        }*/




    }

    @Override
    public int getItemCount() {
        return dadosClimaArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDia, tvDescricao, tvTemp;
        private ImageView ivIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDia = itemView.findViewById(R.id.tvDia);
            tvDescricao = itemView.findViewById(R.id.tvDescricaoPrev);
            tvTemp = itemView.findViewById(R.id.tvMaxMin);
            ivIcon = itemView.findViewById(R.id.ivteste);



        }
    }


}










