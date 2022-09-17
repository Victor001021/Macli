package com.vitor.alcan.appclima;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClimaDiaSelecionado extends AppCompatActivity {

    private ArrayList<DadosClimaDias> dadosClimaDiasArrayList;
    private ImageView ivIconeDiaSelecionado,tvFecharDiaSelecionado;
    private TextView tvDescricaoDiaSelecionado, tvTempmaxSelecionado, tvTempminSelecionado, tvUmidadeDiaSelecionado,tvVelVentoDiaSelecionado, tvPressaoDiaSelecionado,
            tvNebulosidadeDiaSelecionado, tvPopChuvaDiaSelecionado, tvDiaSelecionado;
    private int i;
    private RecyclerView rvDias;
    private DiasAdapter diasAdapter;
    private float x1,x2;
    static final int MIN_DISTANCE = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_dia_selecionado);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        dadosClimaDiasArrayList = new ArrayList<>();

        dadosClimaDiasArrayList = (ArrayList<DadosClimaDias>) getIntent().getSerializableExtra("dados");
        i = (int) getIntent().getSerializableExtra("indice");

        centerTitle();
        //getSupportActionBar().setTitle(dadosClimaDiasArrayList.get(i).getDia());
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_diaselecionado);


        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);*/


        ivIconeDiaSelecionado = findViewById(R.id.ivIconeDiaSelecionado);
        tvDescricaoDiaSelecionado = findViewById(R.id.tvDescricaoDiaSelecionado);
        tvTempmaxSelecionado = findViewById(R.id.tvTempmaxSelecionado);
        tvTempminSelecionado = findViewById(R.id.tvTempminSelecionado);
        tvPopChuvaDiaSelecionado = findViewById(R.id.tvPopChuvaDiaSelecionado);
        tvUmidadeDiaSelecionado = findViewById(R.id.tvUmidadeDiaSelecionado);
        tvVelVentoDiaSelecionado = findViewById(R.id.tvVelVentoDiaSelecionado);
        tvPressaoDiaSelecionado = findViewById(R.id.tvPressaoDiaSelecionado);
        tvNebulosidadeDiaSelecionado = findViewById(R.id.tvNebulosidadeDiaSelecionado);
        tvDiaSelecionado = findViewById(R.id.tvDiaSelecionado);
        tvFecharDiaSelecionado = findViewById(R.id.ivFecharDiaSelecionado);
        rvDias = findViewById(R.id.rvDias);
        diasAdapter = new DiasAdapter(dadosClimaDiasArrayList,this);
        rvDias.setAdapter(diasAdapter);

        inserirDados();

        rvDias.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvDias, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                i = position;
                inserirDados();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        tvFecharDiaSelecionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    /*@Override
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
                            inserirDados();
                        }

                        //Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    }

                    // Right to left swipe action
                    else
                    {
                        if(i < 7){
                            i++;
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
        tvDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getDia());
        //getSupportActionBar().setTitle(dadosClimaDiasArrayList.get(i).getDia());
        Picasso.with(getApplicationContext())
                .load("https://openweathermap.org/img/wn/" + dadosClimaDiasArrayList.get(i).getIcone() + "@4x.png")
                .into(ivIconeDiaSelecionado);

        tvDescricaoDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getDescricao().substring(0,1).toUpperCase() + dadosClimaDiasArrayList.get(i).getDescricao().substring(1));
        tvTempmaxSelecionado.setText(dadosClimaDiasArrayList.get(i).getTempMax() + " °C");
        tvTempminSelecionado.setText(dadosClimaDiasArrayList.get(i).getTempMin() + " °C");
        tvPopChuvaDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getPopChuva() + " %");
        tvUmidadeDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getUmidade() + " %");
        tvVelVentoDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getVelVento() + " Km/h");
        tvPressaoDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getPressao() + " mb");
        tvNebulosidadeDiaSelecionado.setText(dadosClimaDiasArrayList.get(i).getNebulosidade() + " %");
    }

    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        Animatoo.animateSlideDown(ClimaDiaSelecionado.this);
    }

}