package com.vitor.alcan.appclima;

import java.util.List;

public interface IcidadeDao {

    public boolean salvar(Cidade cidade);
    public List<Cidade> listar();
    public void deletar();

}
