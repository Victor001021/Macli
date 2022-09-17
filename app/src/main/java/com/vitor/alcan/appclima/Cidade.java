package com.vitor.alcan.appclima;

import java.io.Serializable;

public class Cidade implements Serializable {
    Long id;
    String cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
