package com.vitor.alcan.appclima;

public class PesquisaClima {
    private String nomeCidade;
    private boolean pesquisar;

    public PesquisaClima(String nomeCidade, boolean pesquisar) {
        this.nomeCidade = nomeCidade;
        this.pesquisar = pesquisar;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }


    public boolean isPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(boolean pesquisar) {
        this.pesquisar = pesquisar;
    }
}
