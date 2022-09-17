package com.vitor.alcan.appclima;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CidadesDao implements IcidadeDao {

    private SQLiteDatabase escreve, le;
    public static String DB = "cidade";


    public CidadesDao(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }


    @Override
    public boolean salvar(Cidade cidade) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", cidade.getCidade());


            Cursor c = le.rawQuery("SELECT * FROM cidade WHERE nome='" + cidade.getCidade() + "'", null);
            if (c.moveToFirst()) {
                //showMessage("Error", "Record exist");
                Log.i("Erro: ", "Cidade já na tabela");
                return false;
            } else {
                escreve.insert(DbHelper.DB, null, cv);
                Log.i("Info: ", "Cidade salva com sucesso!");
            }


        } catch (Exception e) {
            Log.e("Info: ", "Erro ao salvar cidade" + e.getMessage());
            return false;
        }

        return true;
    }


    @Override
    public List<Cidade> listar() {

        List<Cidade> cidades = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.DB + " ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {
            Cidade cidade = new Cidade();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeCidade = c.getString(c.getColumnIndex("nome"));


            cidade.setId(id);
            cidade.setCidade(nomeCidade);

            cidades.add(cidade);
        }

        return cidades;

    }

    @Override
    public void deletar() {
        escreve.execSQL("DELETE FROM " + DB);
        escreve.execSQL("VACUUM");
    }
}
