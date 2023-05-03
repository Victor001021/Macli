package com.vitor.alcan.appclima;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationManager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class Clima extends AppCompatActivity{

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String url2 = "https://api.openweathermap.org/data/2.5/onecall";
    private final String url3 = "https://api.openweathermap.org/data/2.5/air_pollution";
    private String alerta = "", descricaoAlerta = "";
    private final String appid = "a40179b4d7c3fed887951b84072677da";
    private TextView tvDescricao, tvTemp, tvSensacao, tvUmidade, tvVelocidadeVento, tvPressao, tvNebulosidade, tvChuva, tvAqi, tvPopSituacao, tvAlerta, tvPrevHora, tvCidadeClima;
    private LinearLayout lyChuva, lvAlerta;
    private ConstraintLayout cl;

    private FusedLocationProviderClient mFusedLocationClient;
    private int locationRequestCode = 1000;

    private String latitude, longitude;
    private String cidade, cidadePais, pesquisar = "";

    private RecyclerView recyclerView;
    private PrevisaoAdapter previsaoAdapter;
    private ArrayList<DadosClima> dadosClimaArrayList;
    /*********************** Teste ***************************/
    private ArrayList<DadosClimaDias> dadosClimaDiasArrayList;
    private ArrayList<DadosHora> dadosHoraArrayList;
    /********************************************************/
    private Bundle extra;
    private ImageView ivIcone, ivLocalizar, ivPesquisarClima;
    private LinearLayout linearLayout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //handleSSLHandshake();

       /* centerTitle();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_my_location_24);*/

       /**************************************************************************************/

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_clima);

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
       /* SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String currentDateandTime = sdf.format(new Date());*/

        /*Calendar c = Calendar.getInstance();
        int sHour = c.get(Calendar.HOUR_OF_DAY);

        c.add(Calendar.HOUR_OF_DAY, 14);
        sHour = c.get(Calendar.HOUR_OF_DAY);


        Toast.makeText(getApplicationContext(), String.valueOf(sHour), Toast.LENGTH_LONG).show();*/


        /*statusBar translúcida
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        Fullscreen
       /* getWindow().setFlags(WindowManager.LayoutParams.FLA*+*G_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        tvDescricao = findViewById(R.id.tvDescricao);
        tvSensacao = findViewById(R.id.tvSensacao);
        tvTemp = findViewById(R.id.tvTemp);
        tvUmidade = findViewById(R.id.tvUmidade);
        tvVelocidadeVento = findViewById(R.id.tvVelocidadeVento);
        tvPressao = findViewById(R.id.tvPressao);
        tvNebulosidade = findViewById(R.id.tvNebulosidade);
        tvAqi = findViewById(R.id.tvAqi);
        tvPopSituacao = findViewById(R.id.tvPopSituacao);
        tvChuva = findViewById(R.id.tvChuva);
        lyChuva = findViewById(R.id.lyChuva);
        cl = findViewById(R.id.CL);
        recyclerView = findViewById(R.id.rvPrevisao);
        tvPrevHora = findViewById(R.id.tvPrevHora);
        tvAlerta = findViewById(R.id.tvAlerta);
        lvAlerta = findViewById(R.id.lvAlerta);
        ivLocalizar = findViewById(R.id.ivLocalizar);
        ivPesquisarClima = findViewById(R.id.ivPesquisarClima);
        tvCidadeClima = findViewById(R.id.tvCidadeClima);

        /*********************** Teste ***************************/

        dadosClimaDiasArrayList = new ArrayList<>();
        dadosClimaDiasArrayList.clear();
        dadosHoraArrayList = new ArrayList<>();
        dadosHoraArrayList.clear();

        /*******************************************************/

        dadosClimaArrayList = new ArrayList<>();

        previsaoAdapter = new PrevisaoAdapter(this, dadosClimaArrayList);
        recyclerView.setAdapter(previsaoAdapter);
        ivIcone = findViewById(R.id.ivIcone);

        dadosClimaArrayList.clear();

        tvDescricao.setCompoundDrawablePadding(10);


        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.getBackground().setAlpha(200);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        extra = getIntent().getExtras();
        if (extra != null) {
            cidade = extra.getString("cidade");
            pesquisar = extra.getString("pesquisar");
        }

        pesquisarCidade(cidade);



        lvAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), alerta, Toast.LENGTH_LONG).show();

                final AlertDialog.Builder dialog = new AlertDialog.Builder(Clima.this, R.style.DialogTema);

                dialog.setTitle(descricaoAlerta);
                dialog.setIcon(R.drawable.ic_baseline_warning_24);
                dialog.setMessage(alerta);
                dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();


            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Clima.this, ClimaDiaSelecionado.class);
                intent.putExtra("dados", dadosClimaDiasArrayList);
                intent.putExtra("indice", position);
                startActivity(intent);
                Animatoo.animateSlideUp(Clima.this);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        tvPrevHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clima.this, PrevisaoHora.class);
                intent.putExtra("cidadePais", cidadePais);
                intent.putExtra("dados", dadosHoraArrayList);
                startActivity(intent);
                Animatoo.animateSlideUp(Clima.this);
            }
        });

        ivLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

        ivPesquisarClima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clima.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }
        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                checkPermissions();
                break;

            case R.id.itemPesquisar:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);


        }


        return super.onOptionsItemSelected(item);
    }*/


    public void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Clima.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, locationRequestCode);
        } else {
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "Permissão obtida", Toast.LENGTH_LONG).show();
                    getLocation();
                } else {
                    //Toast.makeText(this, "Permissão negada", Toast.LENGTH_LONG).show();
                    
                }
            }
        }

    }

    @SuppressLint("MissingPermission")
    public void getLocation() {

        if (isLocationEnabled()) {
            mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location == null) {
                        requestNewLocationData();
                    } else {
                        latitude = String.valueOf(location.getLatitude());
                        longitude = String.valueOf(location.getLongitude());

                        getWeatherDetails(latitude, longitude);

                    }
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Por favor, ative o GPS do dispositivo!", Toast.LENGTH_LONG).show();
        }


    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
        }
    };

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void getWeatherDetails(String lat, String lon) {


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


            String tempUrl = "", tempUrl2 = "", tempUrl3 = "";

            tempUrl = url + "?lat=" + lat + "&lon=" + lon + "&lang=pt_br&appid=" + appid;


            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    /*Log.d("Response: ", response);
                    Log.d("lat: ", lat);
                    Log.d("lon: ", lon);*/

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);

                        String description = jsonObjectWeather.getString("description");

                        String icon = jsonObjectWeather.getString("icon");
                        Picasso.get().load("http://openweathermap.org/img/wn/" + icon + "@4x.png")
                                .into(ivIcone);

                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        int temperatura = (int) temp;

                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        int sensacaoTermica = (int) feelsLike;

                        int pressure = jsonObjectMain.getInt("pressure");

                        int humidity = jsonObjectMain.getInt("humidity");

                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");

                        int velocidadeVento = (int) (jsonObjectWind.getInt("speed") * 3.6);

                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");

                        String clouds = jsonObjectClouds.getString("all");

                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");

                        String countryName = jsonObjectSys.getString("country");


                        if (jsonResponse.has("rain")) {
                            JSONObject jsonObjectChuva = jsonResponse.getJSONObject("rain");
                            double chuvamm = jsonObjectChuva.getDouble("1h");
                            tvChuva.setText(chuvamm + " mm");
                            lyChuva.setVisibility(View.VISIBLE);
                        } else {
                            lyChuva.setVisibility(View.GONE);
                        }

                        String cityName = jsonResponse.getString("name");

                        //getSupportActionBar().setTitle(cityName + ", " + countryName);
                        tvCidadeClima.setText(cityName + ", " + countryName);

                        cidadePais = cityName + ", " + countryName;


                        tvTemp.setText(temperatura + "°C");
                        tvSensacao.setText(sensacaoTermica + " °C");
                        tvUmidade.setText(humidity + " %");
                        tvVelocidadeVento.setText(velocidadeVento + " Km/h");
                        tvPressao.setText(pressure + " mb");
                        tvNebulosidade.setText(clouds + " %");

                        String descricaoFormatado = description.substring(0, 1).toUpperCase() + description.substring(1);

                        tvDescricao.setText(descricaoFormatado);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    Log.i("Info: ", error.toString().trim());

                }
            });


            tempUrl2 = url2 + "?lat=" + lat + "&lon=" + lon + "&units=metric&lang=pt_br&appid=" + appid;


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, tempUrl2, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("daily");
                        JSONArray jsonArrayWeather;
                        JSONObject jsonObjectDescricao;

                        Double uv = response.getJSONObject("current").getDouble("uvi");


                        //Toast.makeText(getApplicationContext(), String.valueOf(uv), Toast.LENGTH_LONG).show();
                        String diaSemana = "";


                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_WEEK);

                        dadosClimaArrayList.clear();
                        for (int i = 0; i <= 7; i++) {
                            JSONObject jsonObjectWeather = jsonArray.getJSONObject(i);
                            int tempMax = jsonObjectWeather.getJSONObject("temp").getInt("max");
                            int tempMin = jsonObjectWeather.getJSONObject("temp").getInt("min");
                            jsonArrayWeather = jsonObjectWeather.getJSONArray("weather");
                            jsonObjectDescricao = jsonArrayWeather.getJSONObject(0);
                            String descricaoPrev = jsonObjectDescricao.getString("description");
                            descricaoPrev = descricaoPrev.substring(0, 1).toUpperCase() + descricaoPrev.substring(1);

                            String icone = jsonObjectDescricao.getString("icon");
                            int umidadeDia = jsonObjectWeather.getInt("humidity");
                            int pressaoDia = jsonObjectWeather.getInt("pressure");
                            int velVento = jsonObjectWeather.getInt("wind_speed");
                            int nebulosidadeDia = jsonObjectWeather.getInt("clouds");
                            double pop = jsonObjectWeather.getDouble("pop");
                            int popChuva = (int) (pop * 100);

                            String tempMaxString = String.valueOf(tempMax);
                            String tempMinString = String.valueOf(tempMin);
                            String tempMaxMin = tempMaxString + "° / " + tempMinString + "°";


                            if (day > 7) {
                                day = 1;
                            }

                            if (i == 0) {
                                diaSemana = "Hoje";
                            } else {
                                switch (day) {
                                    case Calendar.SUNDAY:
                                        diaSemana = "Domingo";
                                        break;
                                    case Calendar.MONDAY:
                                        diaSemana = "Segunda";
                                        break;
                                    case Calendar.TUESDAY:
                                        diaSemana = "Terça";
                                        break;

                                    case Calendar.WEDNESDAY:
                                        diaSemana = "Quarta";
                                        break;

                                    case Calendar.THURSDAY:
                                        diaSemana = "Quinta";
                                        break;

                                    case Calendar.FRIDAY:
                                        diaSemana = "Sexta";
                                        break;

                                    case Calendar.SATURDAY:
                                        diaSemana = "Sábado";
                                        break;
                                }

                            }


                            dadosClimaArrayList.add(new DadosClima(diaSemana, icone, descricaoPrev, tempMaxMin));
                            dadosClimaDiasArrayList.add(new DadosClimaDias(diaSemana, icone, descricaoPrev, tempMax, tempMin, umidadeDia, pressaoDia, velVento, nebulosidadeDia, popChuva));

                            //Log.i("Dados Clima Dias: ", dadosClimaDiasArrayList.get(i).getDia());


                            day++;

                        }
                        previsaoAdapter.notifyDataSetChanged();


                        if(response.has("alerts")){
                            JSONArray jsonArray2 = response.getJSONArray("alerts");
                            descricaoAlerta = jsonArray2.getJSONObject(0).getString("event");
                            alerta = jsonArray2.getJSONObject(0).getString("description");
                            tvAlerta.setText(descricaoAlerta);
                            lvAlerta.setVisibility(View.VISIBLE);
                        }else{
                            lvAlerta.setVisibility(View.GONE);
                        }


                        for(int i = 0; i < 25; i++){
                            JSONArray jsonArray3 = response.getJSONArray("hourly");
                            JSONObject jsonObjectHourly = jsonArray3.getJSONObject(i);

                            String icone = jsonObjectHourly.getJSONArray("weather").getJSONObject(0).getString("icon");
                            String descricao = jsonObjectHourly.getJSONArray("weather").getJSONObject(0).getString("description");
                            int temp = jsonObjectHourly.getInt("temp");
                            int sensacao = jsonObjectHourly.getInt("feels_like");
                            int pressao = jsonObjectHourly.getInt("pressure");
                            int umidade = jsonObjectHourly.getInt("humidity");
                            int precipitacao = jsonObjectHourly.getInt("pop");
                            int velVento = jsonObjectHourly.getInt("wind_speed");
                            int uvIndex = jsonObjectHourly.getInt("uvi");
                            int nebulosidade = jsonObjectHourly.getInt("clouds");

                            dadosHoraArrayList.add(new DadosHora(icone, descricao, temp, sensacao, pressao, umidade, precipitacao, velVento, uvIndex, nebulosidade));

                        }

                        /*JSONArray jsonArray3 = response.getJSONArray("hourly");
                        JSONObject jsonObjectHourly = jsonArray3.getJSONObject(0);

                        int umidade = jsonObjectHourly.getInt("humidity");

                        Toast.makeText(getApplicationContext(), String.valueOf(umidade), Toast.LENGTH_LONG).show();*/


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });



            /*StringRequest stringRequest2 = new StringRequest(Request.Method.POST, tempUrl2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("daily");
                        JSONArray jsonArrayWeather;
                        JSONObject jsonObjectDescricao;

                        Double uv = jsonResponse.getJSONObject("current").getDouble("uvi");


                        //Toast.makeText(getApplicationContext(), String.valueOf(uv), Toast.LENGTH_LONG).show();
                        String diaSemana = "";


                        Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_WEEK);

                        dadosClimaArrayList.clear();
                        for (int i = 0; i <= 7; i++) {
                            JSONObject jsonObjectWeather = jsonArray.getJSONObject(i);
                            int tempMax = jsonObjectWeather.getJSONObject("temp").getInt("max");
                            int tempMin = jsonObjectWeather.getJSONObject("temp").getInt("min");
                            jsonArrayWeather = jsonObjectWeather.getJSONArray("weather");
                            jsonObjectDescricao = jsonArrayWeather.getJSONObject(0);
                            String descricaoPrev = jsonObjectDescricao.getString("description");
                            descricaoPrev = descricaoPrev.substring(0, 1).toUpperCase() + descricaoPrev.substring(1);
                            String icone = jsonObjectDescricao.getString("icon");

                            String tempMaxString = String.valueOf(tempMax);
                            String tempMinString = String.valueOf(tempMin);
                            String tempMaxMin = tempMaxString + "° / " + tempMinString + "°";


                            if (day > 7) {
                                day = 1;
                            }

                            if (i == 0) {
                                diaSemana = "Hoje";
                            } else {
                                switch (day) {
                                    case Calendar.SUNDAY:
                                        diaSemana = "Domingo";
                                        break;
                                    case Calendar.MONDAY:
                                        diaSemana = "Segunda";
                                        break;
                                    case Calendar.TUESDAY:
                                        diaSemana = "Terça";
                                        break;

                                    case Calendar.WEDNESDAY:
                                        diaSemana = "Quarta";
                                        break;

                                    case Calendar.THURSDAY:
                                        diaSemana = "Quinta";
                                        break;

                                    case Calendar.FRIDAY:
                                        diaSemana = "Sexta";
                                        break;

                                    case Calendar.SATURDAY:
                                        diaSemana = "Sábado";
                                        break;
                                }

                            }


                            dadosClimaArrayList.add(new DadosClima(diaSemana, icone, descricaoPrev, tempMaxMin));
                            dadosClimaDiasArrayList.add(new DadosClimaDias(diaSemana, icone, descricaoPrev, tempMax, tempMin));

                            Log.i("Dados Clima Dias: ", dadosClimaDiasArrayList.get(i).getDia());


                            day++;

                        }
                        previsaoAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })*/


            tempUrl3 = url3 + "?lat=" + lat + "&lon=" + lon + "&appid=" + appid;

            StringRequest stringRequest3 = new StringRequest(Request.Method.GET, tempUrl3, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("list");
                        JSONObject jsonObjectPolution = jsonArray.getJSONObject(0);

                        int aqi = jsonObjectPolution.getJSONObject("main").getInt("aqi");
                        String valorAqi = String.valueOf(aqi);
                        tvAqi.setText(valorAqi + "\nAQI");

                        if (aqi == 1) {
                            tvPopSituacao.setText("Excelente");
                            tvPopSituacao.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icone_bom);
                        } else if (aqi == 2) {
                            tvPopSituacao.setText("Bom");
                            tvPopSituacao.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icone_bom);
                        } else if (aqi == 3) {
                            tvPopSituacao.setText("Razoável");
                            tvPopSituacao.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icone_razoavel);
                        } else if (aqi == 4) {
                            tvPopSituacao.setText("Ruim");
                            tvPopSituacao.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icone_ruim);
                        } else if (aqi == 5) {
                            tvPopSituacao.setText("Péssimo");
                            tvPopSituacao.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.icone_pessimo);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });



            StringRequest stringRequest4 = new StringRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=minutely,hourly,daily,current&appid=a40179b4d7c3fed887951b84072677da", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if(jsonResponse.has("alerts")){
                            JSONArray jsonArray = jsonResponse.getJSONArray("alerts");
                            descricaoAlerta = jsonArray.getJSONObject(0).getString("event");
                            alerta = jsonArray.getJSONObject(0).getString("description");
                            tvAlerta.setText(descricaoAlerta);
                            lvAlerta.setVisibility(View.VISIBLE);
                        }else{
                            lvAlerta.setVisibility(View.GONE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });




            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
            requestQueue.add(request);
           // requestQueue.add(stringRequest2);
            requestQueue.add(stringRequest3);
           // requestQueue.add(stringRequest4);


        } else {
            Toast.makeText(getApplicationContext(), "Sem internet", Toast.LENGTH_LONG).show();
        }


    }

    public void pesquisarCidade(String cidade) {


        if (cidade != null && pesquisar != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                String tempUrl = url + "?q=" + cidade.toLowerCase() + "&appid=" + appid;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject jsonObjectWeather = jsonResponse.getJSONObject("coord");

                            String latitude = jsonObjectWeather.getString("lat");
                            String longitude = jsonObjectWeather.getString("lon");

                            getWeatherDetails(latitude, longitude);


                            String cidade = jsonResponse.getString("name");
                            CidadesDao cidadesDao = new CidadesDao(getApplicationContext());
                            Cidade cidadeDb = new Cidade();
                            cidadeDb.setCidade(cidade);

                            cidadesDao.salvar(cidadeDb);

                            /*if( cidadesDao.salvar(cidadeDb)){//Exibe mensagem de sucesso
                                //Toast.makeText(getApplicationContext(), "Cidade salva com sucesso! " + cidadeDb.getCidade(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Erro ao salvar cidade!", Toast.LENGTH_SHORT).show();

                            }*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(getApplicationContext(), "Sem internet", Toast.LENGTH_LONG).show();
            }
        } else {
            checkPermissions();
        }


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

    /*public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }*/


/*
    @SuppressLint("TrulyRandom")
    private void bypassSSLValidation() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (NoSuchAlgorithmException | KeyManagementException ex ) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
*/


    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    try {
                        chain[0].checkValidity();
                    } catch (Exception e) {
                        throw new CertificateException("Certificate not valid or trusted.");
                    }
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }



}