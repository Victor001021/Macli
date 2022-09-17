package com.vitor.alcan.appclima;

import java.io.Serializable;

public class DadosHora implements Serializable {
    private String icone, descricao;
    private int temp, sensacao, pressao, umidade, prop, velVento, indiceUv, nebulosidade;

    public DadosHora(String icone, String descricao, int temp, int sensacao, int pressao, int umidade, int prop, int velVento, int indiceUv, int nebulosidade) {
        this.icone = icone;
        this.descricao = descricao;
        this.temp = temp;
        this.sensacao = sensacao;
        this.pressao = pressao;
        this.umidade = umidade;
        this.prop = prop;
        this.velVento = velVento;
        this.indiceUv = indiceUv;
        this.nebulosidade = nebulosidade;
    }

    public String getIcone() {
        return icone;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTemp() {
        return temp;
    }

    public int getSensacao() {
        return sensacao;
    }

    public int getPressao() {
        return pressao;
    }

    public int getUmidade() {
        return umidade;
    }

    public int getProp() {
        return prop;
    }

    public int getVelVento() {
        return velVento;
    }

    public int getIndiceUv() {
        return indiceUv;
    }

    public int getNebulosidade() {
        return nebulosidade;
    }

    public void setNebulosidade(int nebulosidade) {
        this.nebulosidade = nebulosidade;
    }
}
