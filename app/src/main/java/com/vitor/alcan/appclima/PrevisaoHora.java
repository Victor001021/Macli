package com.vitor.alcan.appclima;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class PrevisaoHora extends AppCompatActivity {
    private String cidade;
    private ArrayList<DadosHora> dadosHoraArrayList;
    private TextView tvDescricaoHora, tvTempHora, tvPopChuvaHora, tvUmidadeHora, tvVelVentoHora, tvPressaoHora, tvNebulosidadeHora, tvHora;
    private ImageView ivIconeHora, ivHoraApos, ivHoraAnterior, ivFechar;
    private HoraAdapter horaAdapter;
    private RecyclerView rvHora;
    private Calendar c;
    private int i;
    private float x1,x2;
    static final int MIN_DISTANCE = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previsao_hora);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_diaselecionado);

        cidade = (String) getIntent().getSerializableExtra("cidadePais");
        c = Calendar.getInstance();


        dadosHoraArrayList = new ArrayList<>();
        dadosHoraArrayList.clear();
        dadosHoraArrayList = (ArrayList<DadosHora>) getIntent().getSerializableExtra("dados");



        i = 0;




        tvDescricaoHora = findViewById(R.id.tvDescricaoHora);
        ivIconeHora = findViewById(R.id.ivIconeHora);
        tvTempHora = findViewById(R.id.tvTempHora);
        tvPopChuvaHora = findViewById(R.id.tvPopChuvaHora);
        tvUmidadeHora = findViewById(R.id.tvUmidadeHora);
        tvVelVentoHora = findViewById(R.id.tvVelVentoHora);
        tvPressaoHora = findViewById(R.id.tvPressaoHora);
        tvNebulosidadeHora = findViewById(R.id.tvNebulosidadeHora);
        tvHora = findViewById(R.id.tvDiaSelecionado);
        ivFechar = findViewById(R.id.ivFecharDiaSelecionado);

        inserirDados();

        rvHora = findViewById(R.id.rvHora);
        horaAdapter = new HoraAdapter(this, dadosHoraArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvHora.setAdapter(horaAdapter);

        rvHora.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvHora, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                i = position;
                c = Calendar.getInstance();
                c.add(Calendar.HOUR_OF_DAY, i);
                inserirDados();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));


        ivFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        if(i > 0){
                            i--;
                            c.add(Calendar.HOUR_OF_DAY, -1);
                            inserirDados();
                        }

                        //Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    }

                    // Right to left swipe action
                    else
                    {
                        if(i < dadosHoraArrayList.size() - 1){
                            i++;
                            c.add(Calendar.HOUR_OF_DAY, 1);
                            inserirDados();
                        }

                        //Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }*/


    private void inserirDados(){




            int sHour = c.get(Calendar.HOUR_OF_DAY);

            //getSupportActionBar().setTitle(String.valueOf(sHour) + ":00");

            tvHora.setText(String.valueOf(sHour) + ":00");



            /*Picasso.with(getApplicationContext())
                    .load("https://openweathermap.org/img/wn/" + dadosHoraArrayList.get(i).getIcone() + "@4x.png")
                    .into(ivIconeHora);*/

        Picasso.get()
                .load("http://openweathermap.org/img/wn/" + dadosHoraArrayList.get(i).getIcone() + "@4x.png")
                .into(ivIconeHora);

            tvDescricaoHora.setText(dadosHoraArrayList.get(i).getDescricao().substring(0,1).toUpperCase() + dadosHoraArrayList.get(i).getDescricao().substring(1));
            tvTempHora.setText(dadosHoraArrayList.get(i).getTemp() + " °C");
            tvPopChuvaHora.setText(dadosHoraArrayList.get(i).getProp() + " %");
            tvUmidadeHora.setText(dadosHoraArrayList.get(i).getUmidade() + " %");
            tvVelVentoHora.setText(dadosHoraArrayList.get(i).getVelVento() + " Km/h");
            tvPressaoHora.setText(dadosHoraArrayList.get(i).getPressao() + " mb");
            tvNebulosidadeHora.setText(dadosHoraArrayList.get(i).getNebulosidade() + " %");


        }


    @Override
    public void finish() {
        super.finish();
        Animatoo.animateSlideDown(PrevisaoHora.this);
    }
}