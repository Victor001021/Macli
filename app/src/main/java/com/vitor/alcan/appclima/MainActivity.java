package com.vitor.alcan.appclima;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private EditText etCidade;
    private TextView tvRecentes;
    private ImageView ivPesquisa, ivFecharPesquisa;
    private RecyclerView rvCidades;
    private List<Cidade> listaCidades = new ArrayList<>();
    private CidadesAdapter cidadesAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_pesquisa);
        getSupportActionBar().setElevation(0);



        etCidade = findViewById(R.id.etCidade);
        tvRecentes = findViewById(R.id.tvRecentes);
        ivPesquisa = findViewById(R.id.ivPesquisa);
        rvCidades = findViewById(R.id.rvCidade);
        ivFecharPesquisa = findViewById(R.id.ivFecharPesquisa);

        DbHelper db = new DbHelper(getApplicationContext());


        etCidade.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                    abrirTela();
                }

                return false;
            }
        });

        tvRecentes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if(motionEvent.getRawX() >= tvRecentes.getRight() - tvRecentes.getTotalPaddingRight()) {
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.DialogTema);

                        if(!listaCidades.isEmpty()){
                            dialog.setTitle("Excluir Histórico");
                            dialog.setIcon(R.drawable.ic_baseline_delete_24);
                            dialog.setMessage("Deseja excluir o histórico?");
                            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    excluirHistorico();

                                }
                            });
                            dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            AlertDialog alertDialog = dialog.create();
                            alertDialog.show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Não há histórico!", Toast.LENGTH_SHORT).show();
                        }


                        return true;
                    }
                }

                return true;
            }
        });

        ivPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTela();
            }
        });

        rvCidades.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvCidades, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Cidade cidadeSelecionada = listaCidades.get(position);

                Intent intent = new Intent(getApplicationContext(), Clima.class);
                intent.putExtra("cidade", cidadeSelecionada.getCidade());
                intent.putExtra("pesquisar", "sim");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                finish();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        ivFecharPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void excluirHistorico() {
        CidadesDao cidadesDao = new CidadesDao(getApplicationContext());
        cidadesDao.deletar();
        carregarlista();

    }

    public void carregarlista() {
        CidadesDao cidadesDao = new CidadesDao(getApplicationContext());

        listaCidades = cidadesDao.listar();

        cidadesAdapter = new CidadesAdapter(listaCidades);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvCidades.setLayoutManager(layoutManager);
        rvCidades.setHasFixedSize(true);

        rvCidades.setAdapter(cidadesAdapter);

        if(listaCidades.isEmpty()){
            tvRecentes.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_delete2, 0);
        }else{
            tvRecentes.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_delete_24, 0);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarlista();
    }

    public void abrirTela() {
        if (etCidade.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Digite o nome da cidade!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), Clima.class);
            intent.putExtra("cidade", etCidade.getText().toString());
            intent.putExtra("pesquisar", "sim");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            finish();
        }


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }
}