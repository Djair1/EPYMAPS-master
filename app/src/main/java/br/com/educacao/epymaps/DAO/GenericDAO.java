package br.com.educacao.epymaps.DAO;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.educacao.epymaps.R;

/**
 * Created by Jaelson on 25/04/2017.
 */

public abstract class GenericDAO<T> extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bdepymaps";
    private static final int VERSAO_BANCO = 1;
    private Context context;
    private String sqlCreateEstado = "CREATE TABLE IF NOT EXISTS estado(" +
            "idEstado INTEGER PRIMARY KEY AUTOINCREMENT," +
            "codigoUf INT NOT NULL," +
            "nome VARCHAR (50) NOT NULL," +
            "uf CHAR (2) NOT NULL," +
            "regiao INT NOT NULL" +
            ");";

    private String sqlCreateMunicipio = "CREATE TABLE IF NOT EXISTS municipio (" +
            "idMunicipio INTEGER PRIMARY KEY  AUTOINCREMENT," +
            "codigo INT NOT NULL," +
            "nome VARCHAR(255) NOT NULL," +
            "uf CHAR(2)  NOT NULL" +
            ");";

    private String sqlCreateUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
            "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(45) NOT NULL," +
            "email VARCHAR(60) NOT NULL UNIQUE," +
            "senha VARCHAR(30) NOT NULL," +
            "sexo VARCHAR(9) NOT NULL," +
            "cidade VARCHAR(45) NOT NULL," +
            "estado VARCAHR(45) NOT NULL," +
            "status BOOLEAN," +
            "administrador BOOLEAN DEFAULT FALSE" +
            ");";

    private String sqlCreateFichaUsuario = "CREATE TABLE IF NOT EXISTS fichaDiaria(" +
            "idFicha INTEGER PRIMARY KEY AUTOINCREMENT," +
            "statusUsuario VARCHAR(6) NOT NULL," +
            "descricao VARCHAR(100) DEFAULT 'Sem descrição'," +
            "data DATE NOT NULL,"+
            "idCliente INTEGER," +
            "FOREIGN KEY(idCliente) REFERENCES usuario(idCliente));";


    public GenericDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateEstado);
        db.execSQL(sqlCreateMunicipio);
        db.execSQL(sqlCreateUsuario);
        db.execSQL(sqlCreateFichaUsuario);
        try {
            inserirEstados(db);
            inserirMunicipios(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlCreateUsuario);
        db.execSQL(sqlCreateEstado);
        db.execSQL(sqlCreateMunicipio);
        db.execSQL(sqlCreateFichaUsuario);
        try {
            inserirEstados(db);
            inserirMunicipios(db);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void inserirMunicipios(SQLiteDatabase db) {
        final Resources res = context.getResources();
        InputStream is = res.openRawResource(R.raw.estados);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] strings = TextUtils.split(linha, ";");
                db.execSQL(linha);
                Log.e("Teste", "Erro : " + strings[0].trim());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void inserirEstados(SQLiteDatabase db) throws IOException {
        final Resources res = context.getResources();
        InputStream is = res.openRawResource(R.raw.municipios);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] strings = TextUtils.split(linha, ";");
                db.execSQL(linha);
                Log.e("Teste", "Erro : " + strings[0].trim());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


