package com.vitor.alcan.appclima;

public class DadosClima {
    private String dia, icone, descricao, temp;

    public DadosClima(String dia, String icone, String descricao, String temp) {
        this.dia = dia;
        this.icone = icone;
        this.descricao = descricao;
        this.temp = temp;
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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
