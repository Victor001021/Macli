package com.vitor.alcan.appclima;

import java.io.Serializable;

public class DadosClimaDias implements Serializable {
    private String dia, icone, descricao;
    private int tempMax, tempMin, umidade, pressao, velVento, nebulosidade, popChuva;


    public DadosClimaDias(String dia, String icone, String descricao, int tempMax, int tempMin, int umidade, int pressao, int velVento, int nebulosidade, int popChuva) {
        this.dia = dia;
        this.icone = icone;
        this.descricao = descricao;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umidade = umidade;
        this.pressao = pressao;
        this.velVento = velVento;
        this.nebulosidade = nebulosidade;
        this.popChuva = popChuva;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getUmidade() {
        return umidade;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public int getPressao() {
        return pressao;
    }

    public void setPressao(int pressao) {
        this.pressao = pressao;
    }

    public int getVelVento() {
        return velVento;
    }

    public void setVelVento(int velVento) {
        this.velVento = velVento;
    }

    public int getNebulosidade() {
        return nebulosidade;
    }

    public void setNebulosidade(int nebulosidade) {
        this.nebulosidade = nebulosidade;
    }

    public int getPopChuva() {
        return popChuva;
    }

    public void setPopChuva(int popChuva) {
        this.popChuva = popChuva;
    }
}
